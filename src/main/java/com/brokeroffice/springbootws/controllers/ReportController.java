package com.brokeroffice.springbootws.controllers;

        import com.brokeroffice.springbootws.entities.*;
        import com.brokeroffice.springbootws.helpers.ApiResponse;
        import com.brokeroffice.springbootws.helpers.AppConfigReader;
        import com.brokeroffice.springbootws.helpers.Dao;
        import com.brokeroffice.springbootws.models.PostId;
        import com.brokeroffice.springbootws.repo.*;
        import lombok.extern.slf4j.Slf4j;

        import org.apache.poi.xssf.usermodel.XSSFCell;
        import org.apache.poi.xssf.usermodel.XSSFRow;
        import org.apache.poi.xssf.usermodel.XSSFSheet;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.util.StringUtils;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import java.io.File;
        import java.io.FileInputStream;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.nio.file.StandardCopyOption;
        import java.sql.ResultSet;
        import java.sql.Statement;
        import java.util.*;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;


@Slf4j  // to have something printed on the console

@RestController
public class ReportController implements ImplReports {
    @Autowired
    private AppConfigReader appConfigReader;

    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    VideoTutorialsRepo videoTutorialsRepo;
    @Autowired
    PdfTutorialsRepo pdfTutorialsRepo;
    @Autowired
    DealsRepo dealsRepo;

    @Autowired
    UsersRepo usersRepo;
    @Autowired
    UsersTypeRepo usersTypeRepo;


    @Override
    public ApiResponse login(Users users) throws Exception {
           Optional<Users> usersOptional = Optional.ofNullable(usersRepo.findByPhone(users.getPhone()));
            if (usersOptional.isPresent()) {
                Users users1 = usersOptional.get();



                String password = users.getPassword();

                if (password.equals(users1.getPassword())) {
                    Users newReturnUser= new Users();
                    newReturnUser.setId(users1.getId());
                    newReturnUser.setPhone(users1.getPhone());
                    newReturnUser.setUserType(users1.getUserType());
                    return ApiResponse.builder().code(200).message("User Found").data(newReturnUser).build();
                } else {
                    Users newReturnUser= new Users();
                    newReturnUser.setId(0L);
                    newReturnUser.setPhone("");
                    return ApiResponse.builder().code(200).message("User Not Found").data(newReturnUser).build();
                }

            } else {
                return ApiResponse.builder().code(200).message("User Not Found").data(false).build();
            }
    }

    @Override
    public ApiResponse register(Users users) throws Exception {
        Optional<Users> usersOptional = Optional.ofNullable(usersRepo.findByPhone(users.getPhone()));
        if (usersOptional.isPresent()) {
            return ApiResponse.builder().status("403").code(403).message("User Already Exist").data(new Users(1L,"","",new UserTypes(1L,"")) ).build();
        } else {
            usersRepo.save(users);
            return ApiResponse.builder().status("200").code(200).message("User Created").data(users).build();
        }
    }

    @Override
    public ApiResponse usertypes() throws Exception {
        List<UserTypes> userTypes = usersTypeRepo.findAll();
        List<UserTypes> filteredUserTypes = userTypes.stream()
                .filter(user -> !"admin".equals(user.getName())) // Exclude "admin"
                .collect(Collectors.toList());

        return ApiResponse.builder().code(200).message("User Created").data(filteredUserTypes).build();
    }

    @Override
    public ApiResponse product(Product product) throws Exception {

        Optional<Product> productOptional = productRepo.findById(product.getId());
        if (productOptional.isPresent()) {
            Product product1 = productOptional.get();
            product1.setName(product.getName());
            productRepo.save(product1);
            return ApiResponse.builder().code(200).message("Product Updated").data(product1).build();
        } else {
            productRepo.save(product);
            return ApiResponse.builder().code(200).message("Product Created").data(product).build();
        }

    }

    @Override
    public ApiResponse product() throws Exception {

        return ApiResponse.builder().code(200).message("Product Created").data(productRepo.findAll()).build();
    }

    @Override
    public ApiResponse product_id(PostId postId) throws Exception {
        Optional<Product> productOptional = Optional.ofNullable(productRepo.findById(postId.getId()));
        if (productOptional.isPresent()) {
            Product product1 = productOptional.get();
            return ApiResponse.builder().code(200).message("Product Deleted").data(product1).build();
        } else {
            return ApiResponse.builder().code(200).message("Product Not Found").data(0).build();
        }
    }

    @Override
    public ApiResponse products(MultipartFile file,Products products) throws Exception {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("check file exte");
        log.info(filename);

        Path path = Paths.get("C:\\inetpub\\wwwroot\\cpm\\" + filename);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Optional<Products> productsOptional = productsRepo.findById(products.getId());
        if (productsOptional.isPresent()) {
            Products products1 = productsOptional.get();


            products1.setPrice(products.getPrice());
            products1.setProduct(products.getProduct());
            products1.setImageUrl(appConfigReader.getFilePath()+filename);
            products1.setAbout(products.getAbout());

            productsRepo.save(products1);
            return ApiResponse.builder().code(200).message("Product Updated").data(products1).build();
        } else {
            productsRepo.save(products);
            return ApiResponse.builder().code(200).message("Product Created").data(products).build();
        }

    }

    @Override
    public ApiResponse products_id(PostId postId) throws Exception {
        Optional<Products> productsOptional = Optional.ofNullable(productsRepo.findById(postId.getId()));
        if (productsOptional.isPresent()) {
            Products products1 = productsOptional.get();
            return ApiResponse.builder().code(200).message("Product Deleted").data(products1).build();
        } else {
            return ApiResponse.builder().code(200).message("Product Not Found").data(0).build();
        }
    }

    @Override
    public ApiResponse products_delete(PostId postId) throws Exception {
        productsRepo.deleteById(postId.getId());
        return ApiResponse.builder().code(200).message("Product Deleted").data(1).build();
    }

    @Override
    public ApiResponse products() throws Exception {
        return ApiResponse.builder().code(200).message("Product Created").data(productsRepo.findAll()).build();
    }

    @Override
    public ApiResponse videos(MultipartFile file,VideoTutorials videoTutorials) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("check file exte");
        log.info(filename);
        if (!filename.endsWith(".wav") && !filename.endsWith(".mp4")) {
            return ApiResponse.builder().code(500).message("File Type Wrong").data(0).build();
        }
        Path path = Paths.get("C:\\inetpub\\wwwroot\\cpm\\" + filename);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Optional<VideoTutorials> videoTutorialsOptional = videoTutorialsRepo.findById(videoTutorials.getId());
        if (videoTutorialsOptional.isPresent()) {
            VideoTutorials videoTutorials1 = videoTutorialsOptional.get();
            videoTutorials1.setTutorialName(videoTutorials.getTutorialName());
            videoTutorials1.setVidNameUrl(appConfigReader.getFilePath()+filename);
            videoTutorialsRepo.save(videoTutorials1);
            return ApiResponse.builder().code(200).message("Product Updated").data(videoTutorials1).build();
        } else {
            VideoTutorials videoTutorials1 = videoTutorials;
            videoTutorials1.setVidNameUrl(appConfigReader.getFilePath()+filename);
            videoTutorialsRepo.save(videoTutorials);
            return ApiResponse.builder().code(200).message("Product Created").data(videoTutorials).build();
        }
    }

    @Override
    public ApiResponse videos() throws Exception {
        return ApiResponse.builder().code(200).message("Product Created").data(videoTutorialsRepo.findAll()).build();
    }

    @Override
    public ApiResponse videos_id(PostId postId) throws Exception {

        Optional<VideoTutorials> videoTutorialsOptional = Optional.ofNullable(videoTutorialsRepo.findById(postId.getId()));
        if (videoTutorialsOptional.isPresent()) {
            VideoTutorials videoTutorials1 = videoTutorialsOptional.get();
            return ApiResponse.builder().code(200).message("Video Found").data(videoTutorials1).build();
        } else {
            return ApiResponse.builder().code(200).message("Video Not Found").data(0).build();
        }
    }

    @Override
    public ApiResponse pdf(MultipartFile file,PdfTutorials pdfTutorials) throws Exception {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("check file exte");
        log.info(filename);
        if (!filename.endsWith(".pdf")) {
            return ApiResponse.builder().code(500).message("File Type Wrong").data(0).build();
        }
        Path path = Paths.get("C:\\inetpub\\wwwroot\\cpm\\" + filename);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Optional<PdfTutorials> pdfTutorialsOptional = pdfTutorialsRepo.findById(pdfTutorials.getId());
        if (pdfTutorialsOptional.isPresent()) {
            PdfTutorials pdfTutorials1 = pdfTutorialsOptional.get();
            pdfTutorials1.setPdfUrl(appConfigReader.getFilePath()+filename);
            pdfTutorials1.setTutorialName(pdfTutorials.getTutorialName());
            pdfTutorialsRepo.save(pdfTutorials1);
            return ApiResponse.builder().code(200).message("Product Updated").data(pdfTutorials1).build();
        } else {
            pdfTutorialsRepo.save(pdfTutorials);
            return ApiResponse.builder().code(200).message("Product Created").data(pdfTutorials).build();
        }
    }

    @Override
    public ApiResponse pdf() throws Exception {
        return ApiResponse.builder().code(200).message("Product Created").data(pdfTutorialsRepo.findAll()).build();
    }

    @Override
    public ApiResponse pdf_id(PostId postId) throws Exception {
        Optional<PdfTutorials> pdfTutorialsOptional = Optional.ofNullable(pdfTutorialsRepo.findById(postId.getId()));
        if (pdfTutorialsOptional.isPresent()) {
            PdfTutorials pdfTutorials1 = pdfTutorialsOptional.get();
            return ApiResponse.builder().code(200).message("Pdf Found").data(pdfTutorials1).build();
        } else {
            return ApiResponse.builder().code(200).message("Pdf Not Found").data(0).build();
        }
    }

    @Override
    public ApiResponse deals(Deals deals) throws Exception {


        Optional<Deals> dealsOptional = dealsRepo.findById(deals.getId());
        if (dealsOptional.isPresent()) {

            Deals deals1 = dealsOptional.get();
            if(deals.isApprove()){
                deals1.setStatus(true);
                dealsRepo.save(deals1);

            }else{
                deals1.setQty(deals.getQty());
                deals1.setAmount(deals.getAmount());
                deals1.setProducts(deals.getProducts());
                deals1.setUsers(deals.getUsers());
                dealsRepo.save(deals1);

            }
            return ApiResponse.builder().code(200).message("Product Updated").data(deals1).build();
        } else {
            dealsRepo.save(deals);
            return ApiResponse.builder().code(200).message("Product Created").data(deals).build();
        }

    }

    @Override
    public ApiResponse deals() throws Exception {
        return  ApiResponse.builder().code(200).message("Deals fetched").data(dealsRepo.findAll()).build();
    }

    @Override
    public ApiResponse dealsApproved(long user_id) throws Exception {
        return  ApiResponse.builder().code(200).message("Deals fetched").data(dealsRepo.dealsStatsApproved(user_id)).build();
    }

    @Override
    public ApiResponse dealsNonApproved(long user_id) throws Exception {
        return  ApiResponse.builder().code(200).message("Deals fetched").data(dealsRepo.dealsStatsNonApproved(user_id)).build();
    }

    //=====================


    @Override
    public ApiResponse dealsAdminApproved() throws Exception {
        long count=0L;
        try{
            String sql="select count(*) as count from deals where status=1 ";

            Statement st= Dao.connection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            int rowCount = rs.getRow();
            while(rs.next())
            {
                count=rs.getLong("count");

            }
            // conn.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return  ApiResponse.builder().code(200).message("Deals fetched").data(count).build();
    }

    @Override
    public ApiResponse dealsAdminNonApproved() throws Exception {
        long count=0L;
        try{
            String sql="select count(*) as count from deals where status=0";

            Statement st= Dao.connection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            int rowCount = rs.getRow();
            while(rs.next())
            {
                count=rs.getLong("count");

            }
            // conn.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return  ApiResponse.builder().code(200).message("Deals fetched").data(count).build();
    }

    @Override
    public ApiResponse SmsUpload(MultipartFile file, String message) throws Exception {
        int trackerNull = 0;
        try {

            // Save file to disk
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            log.info("check file exte");
            log.info(filename);
            if (!filename.endsWith(".xlsx") && !filename.endsWith(".xls")) {
                return ApiResponse.builder().code(500).message("File Type Wrong").data(0).build();
            }
            Path path = Paths.get("C:\\Users\\Leroy Chiyangwa\\Documents\\Kim_Project\\" + filename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            //======================================================
            FileInputStream file2 = new FileInputStream(new File("C:\\Users\\Leroy Chiyangwa\\Documents\\Kim_Project\\" + filename));
            XSSFWorkbook workbook = new XSSFWorkbook(file2);
            XSSFSheet sheet = workbook.getSheetAt(0);


            int totalRows = sheet.getPhysicalNumberOfRows();
            Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
            XSSFRow row = sheet.getRow(0); //Get first row
            //following is boilerplate from the java doc
            short minColIx = row.getFirstCellNum(); //get the first column index for a row
            short maxColIx = row.getLastCellNum(); //get the last column index for a row
            for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
                XSSFCell cell = row.getCell(colIx); //get the cell

            }

            List<Object> listOfDataFromReport = new ArrayList<Object>();

            for(int x = 1; x<=totalRows; x++) {
                XSSFRow dataRow = sheet.getRow(x); //get row 1 to row n (rows containing data)
                Object v =  dataRow.getCell(0);
                Object v2 =  dataRow.getCell(6);
                log.info(v.toString());
                log.info(v2.toString());
               int xc=90;
               trackerNull++;

            }

        //=====================================================
        }catch (Exception e){
            log.info("error");
            log.info(e.getMessage());
            return ApiResponse.builder().code(500).message("File Type Wrong check Index"+trackerNull).data(0).build();
        }
        return ApiResponse.builder().code(200).message("Bulk Message Success").data(1).build();
    }



}
