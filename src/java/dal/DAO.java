/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import javax.mail.PasswordAuthentication;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Cart;
import model.Category;
import model.Item;
import model.Product;
import javax.activation.DataHandler;
import javax.activation.DataSource;

/**
 *
 * @author hii
 */
public class DAO extends DBContext {

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Product_Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            //executeQuery chạy cho select * from Categories
            //còn excuteUpdate chạy cho insert, update, delete
            while (re.next()) {
                Category c = new Category(re.getInt("productCategoryID"), re.getString("category_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;

    }

    public Category getCategoryByID(int id) {
        String sql = "select * from Product_Category where productCategoryID=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            // gán id cho câu lệnh SQL
            ResultSet re = st.executeQuery();
            //tất cả kq của câu lệnh sql lưu vào re
            //sau đó dùng next để tìm xem có thằng nào có id cần tìm ko 

            if (re.next()) {
                Category c = new Category(re.getInt("productCategoryID"), re.getString("category_name"));
                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;

    }

    public List<Product> getProductsByCid(int cid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product where 1=1";
        if (cid != 0) {
            sql += " and productCategoryID=" + cid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getString("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setDescription(rs.getString("Description"));
                p.setImage(rs.getString("Image"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                Category c = getCategoryByID(rs.getInt("ProductCategoryID"));
                p.setProductCategoryID(c);
                list.add(p);

            }
        } catch (SQLException e) {
        }

        return list;

    }

    public Product getProductById(String id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE productID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductID(rs.getString("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setDescription(rs.getString("Description"));
                product.setImage(rs.getString("Image"));
                product.setPrice(rs.getDouble("Price"));
                product.setQuantity(rs.getInt("Quantity"));
                Category category = getCategoryByID(rs.getInt("ProductCategoryID"));
                product.setProductCategoryID(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> search(int cid, int filter, String searchProduct, String searchDetail, Double fromPrice, Double toPrice) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product where 1=1 ";
        if (cid != 0) {
            sql += " and productCategoryID=" + cid;
        }

        if (searchProduct != null && !searchProduct.equals("")) {
            sql += " and productName like N'%" + searchProduct + "%'";
        }
        if (searchDetail != null && !searchDetail.equals("")) {
            sql += " and description like N'%" + searchDetail + "%'";
        }
        if (fromPrice != 0) {
            sql += " and price>=" + fromPrice;
        }
        if (toPrice != 0) {
            sql += " and price<=" + toPrice;
        }
        //bán chạy 
        if (filter == 1) {
            sql += " order by price";
        }
        if (filter == 2) {
            sql += " order by price ";
        }
        if (filter == 3) {
            sql += " order by price desc";
        }
        if (filter == 4) {
            sql += " order by productName";
        }
        if (filter == 5) {
            sql += " order by productName desc";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getString("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setDescription(rs.getString("Description"));
                p.setImage(rs.getString("Image"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                Category c = getCategoryByID(rs.getInt("ProductCategoryID"));
                p.setProductCategoryID(c);
                list.add(p);

            }
        } catch (SQLException e) {
        }

        return list;

    }

    public User check(String username, String password) {
        String sql = "select * from [user] where username = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User a = new User(rs.getInt("userID"), rs.getString("name"),
                        rs.getString("address"), rs.getString("email"), rs.getString("phone"),
                        rs.getString("userName"), rs.getString("password"), rs.getInt("roleID"));
                return a;

            } else {
                return null;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String sql = "select * from [User]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            //executeQuery chạy cho select * from Categories
            //còn excuteUpdate chạy cho insert, update, delete
            while (re.next()) {
                User c = new User(re.getInt("userID"), re.getString("name"), re.getString("address"), re.getString("email"), re.getString("phone"), re.getString("userName"), re.getString("password"), re.getInt("roleID"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;

    }

    public boolean existedUser(String username) {
        String sql = "select * from [User] where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                return true;

            }
        } catch (Exception e) {
        }

        return false;
    }

    public void register(User a) {
        String sql = "INSERT INTO [User] (userID, name, address, email, phone, userName, password, roleID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getUserID());
            st.setString(2, a.getName());
            st.setString(3, a.getAddress());
            st.setString(4, a.getEmail());
            st.setString(5, a.getPhone());
            st.setString(6, a.getUserName());
            st.setString(7, a.getPassword());
            st.setInt(8, a.getRoleID());
            st.executeUpdate();
        } catch (SQLException e) {
            // Xử lý ngoại lệ ở đây, ví dụ:
            e.printStackTrace(); // In ra stack trace của lỗi
            // Hoặc có thể log lỗi hoặc thông báo lỗi ra ngoài đây.
        }
    }

    public void change(User a) {
        String sql = "update [user] set password=? where username =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getPassword());
            st.setString(2, a.getUserName());
            st.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void changeProfile(User a) {
        String sql = "update [user] set name=?,  address=?,  email=?, phone=?  where username =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getName());
            st.setString(2, a.getAddress());
            st.setString(3, a.getEmail());
            st.setString(4, a.getPhone());
            st.setString(5, a.getUserName());
            st.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void addOrder(User u, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into [order] values (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getUserID());
            st.setString(2, date);
            st.setDouble(3, cart.getTotalMoney());
            st.setString(4, "ok");
            st.setInt(5, 1);

            st.executeUpdate();

            String sql1 = "select top 1 orderID from [Order] order by orderID desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [OrderDetail] values(?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setString(2, i.getProduct().getProductID());
                    st2.setInt(3, i.getQuantity());
                    st2.setDouble(4, i.getPrice());
                    st2.executeUpdate();
                }
            }
        } catch (Exception e) {
        }
    }

//    public void updateQuantity(Cart cart) {
//        try {
//            String sql = "update product set quantity = ? where productID =?";
//            PreparedStatement st = connection.prepareCall(sql);
//            for (Object c : cart.getItems()) {
//                
//            }
//        } catch (Exception e) {
//        }
//
//    }
    public void updateQuantity(Cart cart) {
        try {
            String sql = "update product set quantity = ? where productID = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            for (Item item : cart.getItems()) {
                int cartQuantity = item.getQuantity(); // Số lượng trong giỏ hàng
                String productID = item.getProduct().getProductID();
                
                    // Lấy số lượng hiện có trong kho
                int currentQuantity = getCurrentQuantityFromDatabase(productID);

                // Tính toán số lượng mới cần cập nhật trong kho
                int newQuantity = currentQuantity - cartQuantity;
                
                // Cập nhật số lượng trong cơ sở dữ liệu
                st.setInt(1, newQuantity);
                st.setString(2, productID);
                st.executeUpdate();
            }
            // Đóng kết nối và tuyển các ngoại lệ nếu cần
            st.close();
        } catch (SQLException e) {
            // Xử lý các ngoại lệ SQL ở đây
            e.printStackTrace();
        }
    }

// Hàm để lấy số lượng hiện có từ cơ sở dữ liệu
    private int getCurrentQuantityFromDatabase(String productID) throws SQLException {
        String sql = "select quantity from product where productID = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, productID);
        ResultSet rs = st.executeQuery();
        int currentQuantity = 0;
        if (rs.next()) {
            currentQuantity = rs.getInt("quantity");
        }
        rs.close();
        st.close();
        return currentQuantity;
    }

    public User checkEmail(String email) {
        String sql = "select * from [user] where email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User a = new User(rs.getInt("userID"), rs.getString("name"),
                        rs.getString("address"), rs.getString("email"), rs.getString("phone"),
                        rs.getString("userName"), rs.getString("password"), rs.getInt("roleID"));
                return a;

            } else {
                return null;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public static boolean sendMail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tienmanh1609jike@gmail.com", "paup pmrw vrzj zdcv");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("tienmanh1609jike@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(text, "text/html; charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    public boolean passRecovery(String email) {
        User a = checkEmail(email);

        if (a != null) {
            String emailContent = "<html><body>"
                    + "<p>Chào " + a.getName() + ",</p>"
                    + "<p>Chúng tôi nhận được yêu cầu của bạn để lấy lại mật khẩu cho tài khoản tại Shop BiDo Fashion. Dưới đây là mật khẩu mới của bạn:</p>"
                    + "<p>Tên tài khoản: " + a.getUserName() + "</p>"
                    + "<p>Mật khẩu mới: " + a.getPassword() + "</p>"
                    + "<p>Chúng tôi khuyên bạn nên đổi mật khẩu ngay sau khi đăng nhập vào tài khoản của mình để đảm bảo an toàn cho thông tin cá nhân.</p>"
                    + "<p>Nếu bạn có bất kỳ câu hỏi hoặc cần hỗ trợ gì khác, đừng ngần ngại liên hệ với chúng tôi qua email hoặc số điện thoại được cung cấp dưới đây.</p>"
                    + "<p>Email: tienmanh1609jike@gmail.com</p>"
                    + "<p>Điện thoại: 0327499233</p>"
                    + "<p>Chân thành cảm ơn !!!</p>"
                    + "</body></html>";
            sendMail(email, "Password Recovery", emailContent);
            return true;
        }
        return false;
    }

    // ADMIN 
    public void delete(String id) {
        String sql = "delete from orderdetail where productid = ? "
                + "delete from product where productid = ?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, id);
            st.setString(2, id);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Product c) {
        String sql = "UPDATE product\n"
                + "SET productName = ?, \n"
                + "    description = ?, \n"
                + "    image = ?, \n"
                + "    price = ?, \n"
                + "    quantity = ?, \n"
                + "    productCategoryID = ?\n"
                + "WHERE productID = ?;";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setString(1, c.getProductName());
            st.setString(2, c.getDescription());
            st.setString(3, c.getImage());
            st.setDouble(4, c.getPrice());
            st.setInt(5, c.getQuantity());
            st.setInt(6, c.getProductCategoryID().getProductCategoryID());
            st.setString(7, c.getProductID());
            st.executeUpdate();

        } catch (SQLException e) {

        }

    }
    public void insert(Product c) {
    String sql = "INSERT INTO Product (productName, [description], [image], [price], [quantity], productCategoryID, productID) VALUES (?,?,?,?,?,?,?)";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, c.getProductName());
        st.setString(2, c.getDescription());
        st.setString(3, c.getImage());
        st.setDouble(4, c.getPrice());
        st.setInt(5, c.getQuantity());
        st.setInt(6, c.getProductCategoryID().getProductCategoryID());
        st.setString(7, c.getProductID());
        st.executeUpdate();
    } catch (SQLException e) {
        // Xử lý ngoại lệ SQL
        e.printStackTrace();
    }
}

    
    
    

    public static void main(String[] args) {
        DAO d = new DAO();
        System.out.println(d.passRecovery("tiennmhe172825@fpt.edu.vn"));

    }

}
