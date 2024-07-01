package com.org.microservice.order.service.order;

import com.org.microservice.order.dto.order.OrderConfirmation;
import com.org.microservice.order.dto.order.OrderRequest;
import com.org.microservice.order.dto.order.OrderResponse;
import com.org.microservice.order.dto.payment.PaymentRequest;
import com.org.microservice.order.dto.product.PurchaseRequest;
import com.org.microservice.order.exception.BusinessException;
import com.org.microservice.order.kafka.order.OrderProducer;
import com.org.microservice.order.model.order.Order;
import com.org.microservice.order.model.orderline.OrderLine;
import com.org.microservice.order.repository.order.OrderRepository;
import com.org.microservice.order.repository.orderline.OrderLineRepository;
import com.org.microservice.order.service.customer.CustomerService;
import com.org.microservice.order.service.payment.PaymentService;
import com.org.microservice.order.service.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest orderRequest) {
        var customer = customerService.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productService.purchaseProducts(orderRequest.products())
                .orElseThrow(() -> new BusinessException("Some error occurred in between"));

        var order = orderRepository.save(orderMapper.mapToOrder(orderRequest));

        for(PurchaseRequest product : orderRequest.products()) {
            orderLineRepository.save(
                    OrderLine.builder()
                            .productId(product.productId())
                            .order(
                                    Order.builder()
                                            .id(order.getId())
                                            .build()
                            )
                            .quantity(product.quantity())
                            .build()
            );
        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.totalAmount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .paymentMethod(orderRequest.paymentMethod())
                .orderReference(orderRequest.reference())
                .orderId(order.getId())
                .amount(order.getTotalAmount())
                .customer(customer)
                .build();

        Integer paymentId = paymentService.createPayment(paymentRequest);

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Order with id = %d not found",orderId)));
    }
}
