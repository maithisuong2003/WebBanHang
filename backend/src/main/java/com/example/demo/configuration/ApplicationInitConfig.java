package com.example.demo.configuration;

import com.example.demo.constant.PredefinedRole;
import com.example.demo.constant.PredefinedPermission;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.reponsitories.PermissionRepository;
import com.example.demo.reponsitories.RoleRepository;
import com.example.demo.reponsitories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    static final String ADMIN_USER_NAME = "admin";
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring.datasource",
            name = "driver-class-name",
            havingValue = "com.mysql.cj.jdbc.Driver")

    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUserName(ADMIN_USER_NAME).isEmpty()) {
                PermissionEntity adminPanelPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.ADMIN_PANEL).description("Admin panel").build());

                PermissionEntity getProductsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.PRODUCTS_GET).description("Xem danh sách sản phẩm").build());
                PermissionEntity putProductsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.PRODUCTS_PUT).description("Chỉnh sửa sản phẩm").build());
                PermissionEntity postProductsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.PRODUCTS_POST).description("Thêm sản phẩm").build());
                PermissionEntity deleteProductsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.PRODUCTS_DELETE).description("Xóa sản phẩm").build());

                PermissionEntity getOrdersPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.ORDERS_GET).description("Xem danh sách đơn hàng").build());
                PermissionEntity putOrdersPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.ORDERS_PUT).description("Chỉnh sửa đơn hàng").build());
                PermissionEntity postOrdersPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.ORDERS_POST).description("Thêm đơn hàng").build());
                PermissionEntity deleteOrdersPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.ORDERS_DELETE).description("Xóa đơn hàng").build());

                PermissionEntity getSalesPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.SALES_GET).description("Xem danh sách doanh số").build());
                PermissionEntity putSalesPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.SALES_PUT).description("Chỉnh sửa doanh số").build());
                PermissionEntity postSalesPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.SALES_POST).description("Thêm doanh số").build());
                PermissionEntity deleteSalesPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.SALES_DELETE).description("Xóa doanh số").build());

                PermissionEntity getCartPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.CART_GET).description("Xem danh sách giỏ hàng").build());
                PermissionEntity putCartPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.CART_PUT).description("Chỉnh sửa giỏ hàng").build());
                PermissionEntity postCartPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.CART_POST).description("Thêm giỏ hàng").build());
                PermissionEntity deleteCartPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.CART_DELETE).description("Xóa giỏ hàng").build());

                PermissionEntity postAccountsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.USER_POST).description("Thêm tài khoản").build());
                PermissionEntity putAccountsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.USER_PUT).description("Chỉnh sửa tài khoản").build());
                PermissionEntity getAccountsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.USER_GET).description("Xem danh sách tài khoản").build());
                PermissionEntity deleteAccountsPermission = permissionRepository.save(
                        PermissionEntity.builder().name(PredefinedPermission.USER_DELETE).description("Xóa Quản lý tài khoản").build());

                RoleEntity adminRole = roleRepository.save(
                        RoleEntity.builder()
                                .name(PredefinedRole.ADMIN_ROLE)
                                .description("Quản trị viên")
                                .permissions(Set.of(
                                        getProductsPermission, putProductsPermission, postProductsPermission, deleteProductsPermission,
                                        getOrdersPermission, putOrdersPermission, postOrdersPermission, deleteOrdersPermission,
                                        getSalesPermission, putSalesPermission, postSalesPermission, deleteSalesPermission,
                                        getCartPermission, putCartPermission, postCartPermission, deleteCartPermission,
                                        postAccountsPermission, putAccountsPermission, getAccountsPermission, deleteAccountsPermission,
                                        adminPanelPermission
                                ))
                                .build()
                );
                RoleEntity userRole = roleRepository.save(
                        RoleEntity.builder()
                                .name(PredefinedRole.USER_ROLE)
                                .description("Người dùng")
                                .permissions(Set.of(getCartPermission, putCartPermission, postCartPermission, deleteCartPermission))
                                .build()
                );
                RoleEntity productManagerRole = roleRepository.save(
                        RoleEntity.builder()
                                .name(PredefinedRole.PRODUCT_MANAGER_ROLE)
                                .description("Quản lý sản phẩm")
                                .permissions(new HashSet<>())
                                .build()
                );
                RoleEntity orderManagerRole = roleRepository.save(
                        RoleEntity.builder()
                                .name(PredefinedRole.ORDER_MANAGER_ROLE)
                                .description("Quản lý đơn hàng")
                                .permissions(new HashSet<>())
                                .build()
                );
                RoleEntity salesManagerRole = roleRepository.save(
                        RoleEntity.builder()
                                .name(PredefinedRole.SALES_MANAGER_ROLE)
                                .description("Quản lý doanh số")
                                .permissions(new HashSet<>())
                                .build()
                );

                UserEntity adminUser = UserEntity.builder()
                        .userName(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .fullName("Admin")
                        .address("Admin address")
                        .email("admin@gmail.com")
                        .phone("123456789")
                        .isActive(true)
                        .birthday(LocalDate.now())
                        .roles(Set.of(adminRole))
                        .build();

                userRepository.save(adminUser);
                log.warn("Admin user has been created with default password: admin, please change it");
            }
        };
    }
}