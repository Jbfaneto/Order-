package entities;

import entitiesEnum.OrderStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    private Date moment;
    private OrderStatus status;
    private Client client;
    private List<OrderItem> items = new ArrayList<>();

    public Order(OrderStatus status, Client client) {
        this.moment = new Date();
        this.status = status;
        this.client = client;
    }
    public void addOrderItem(OrderItem item) {
        items.add(item);
    }
    public void removeOrderItem(OrderItem item) {
        items.remove(item);
    }
    public Double total() {
        Double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.subTotal();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ORDER SUMMARY:\n");
        sb.append("Order moment: ");
        sb.append(sdf.format(moment));
        sb.append("\n");
        sb.append("Order status: ");
        sb.append(status);
        sb.append("\n");
        sb.append("Client: ");
        sb.append(client.getName());
        sb.append(" (");
        sb.append(sdf2.format(client.getBirthDate()));
        sb.append(") - ");
        sb.append(client.getEmail());
        sb.append("\n");
        sb.append("Order items:\n");
        for (OrderItem item : items) {
            sb.append(item.getProduct().getName());
            sb.append(", $");
            sb.append(String.format("%.2f", item.getProduct().getPrice()));
            sb.append(", Quantity: ");
            sb.append(item.getQuantity());
            sb.append(", Subtotal: $");
            sb.append(String.format("%.2f", item.subTotal()));
            sb.append("\n");
        }
        sb.append("Total price: $");
        sb.append(String.format("%.2f", total()));
        return sb.toString();
    }
}
