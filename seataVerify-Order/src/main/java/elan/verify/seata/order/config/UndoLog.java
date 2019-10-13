//package elan.verify.seata.order.config;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
///**
// * @author 张一然
// * @date 2019/10/12 17:51
// * @Description
// */
//@lombok.Data
//@lombok.NoArgsConstructor
//@Entity
//public class UndoLog {
//    @Id
//    @Column(length = 20)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(length = 20, nullable = false)
//    private Integer branchId;
//
//    @Column(length = 100, nullable = false)
//    private String xid;
//
//    @Column(length = 128, nullable = false)
//    private String context;
//
//    @Lob
//    @Column(columnDefinition = "longblob", nullable = false)
//    private byte[] rollbackInfo;
//
//    @Column(length = 11, nullable = false)
//    private Integer logStatus;
//
//    @Column(nullable = false)
//    private LocalDateTime logCreated;
//
//    @Column(nullable = false)
//    private LocalDateTime logModified;
//
//    @Column(length = 100)
//    private String ext;
//}
