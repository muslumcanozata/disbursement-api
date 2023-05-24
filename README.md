# Loan Disbursement and Repayment

- With this project, it is aimed to manage loan disbursement and its repayment operations. It is aimed to insert users, create credits and their installments, repayment and check overdue of installments.

- This project has a user controller, to manage crud operations for user entity, a credit controller to list credits by filter, 
a disbursement controller to disburse and create credit and its installments, a payback controller to payback credit installments, and there is also a scheduled task to check overdue and recalculate the all installments amounts.

## Tables Diagram

![TablesDiagram](https://i.ibb.co/sgBDVhc/Unknown.png)

## 🛠 Technology Stacks

- Java 20, Spring Boot Framework 3.0.6, PostgreSQL 12, Git, GitHub, Jenkins, Docker

## Project Tree

```
📦 
├─ .DS_Store
├─ .gitignore
├─ Dockerfile
├─ Jenkinsfile
├─ README.md
├─ build.gradle
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ settings.gradle
└─ src
   ├─ .DS_Store
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ loan
   │  │        └─ disbursementapi
   │  │           ├─ DisbursementApiApplication.java
   │  │           ├─ controller
   │  │           │  ├─ CreditController.java
   │  │           │  ├─ DisbursementController.java
   │  │           │  ├─ PaybackController.java
   │  │           │  ├─ UserController.java
   │  │           │  ├─ request
   │  │           │  │  ├─ BatchCreateUserRequest.java
   │  │           │  │  ├─ CreateUserRequest.java
   │  │           │  │  ├─ DisbursementRequest.java
   │  │           │  │  ├─ GetCreditsWithPaginationAndFilterRequest.java
   │  │           │  │  └─ PaybackRequest.java
   │  │           │  └─ response
   │  │           │     ├─ DisbursementResponse.java
   │  │           │     └─ InstallmentResponse.java
   │  │           ├─ dao
   │  │           │  ├─ CreditRepository.java
   │  │           │  ├─ InstallmentRepository.java
   │  │           │  └─ UserRepository.java
   │  │           ├─ domain
   │  │           │  ├─ constant
   │  │           │  │  └─ Constants.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ BaseDTO.java
   │  │           │  │  ├─ CreditDTO.java
   │  │           │  │  ├─ InstallmentDTO.java
   │  │           │  │  └─ UserDTO.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BaseEntity.java
   │  │           │  │  ├─ Credit.java
   │  │           │  │  ├─ Installment.java
   │  │           │  │  └─ User.java
   │  │           │  └─ enums
   │  │           │     ├─ CreditStatus.java
   │  │           │     └─ InstallmentStatus.java
   │  │           ├─ mapper
   │  │           │  ├─ CoreMapper.java
   │  │           │  └─ CoreMapperImpl.java
   │  │           └─ service
   │  │              ├─ CreditService.java
   │  │              ├─ DisbursementService.java
   │  │              ├─ InstallmentService.java
   │  │              ├─ PaybackService.java
   │  │              ├─ UserService.java
   │  │              └─ impl
   │  │                 ├─ CreditServiceImpl.java
   │  │                 ├─ DisbursementServiceImpl.java
   │  │                 ├─ InstallmentServiceImpl.java
   │  │                 ├─ PaybackServiceImpl.java
   │  │                 └─ UserServiceImpl.java
   │  └─ resources
   │     └─ application.yml
   └─ test
      └─ java
         └─ com
            └─ loan
               └─ disbursementapi
                  ├─ controller
                  │  ├─ CreditControllerTest.java
                  │  ├─ DisbursementControllerTest.java
                  │  ├─ PaybackControllerTest.java
                  │  └─ UserControllerTest.java
                  └─ service
                     └─ impl
                        ├─ CreditServiceImplTest.java
                        ├─ DisbursementServiceImplTest.java
                        ├─ InstallmentServiceImplTest.java
                        ├─ PaybackServiceImplTest.java
                        └─ UserServiceImplTest.java
```

## Run Locally

Pull the project image

```bash
  docker pull muslumcanozata/disbursement-api:0.0.1-SNAPSHOT
```

Build the image

```bash
  docker build -t muslumcanozata/disbursement-api:0.0.1-SNAPSHOT .
```

Run the container

```bash
  docker run -d -p 8087:8087 muslumcanozata/disbursement-api:0.0.1-SNAPSHOT 
```

You can go to the swagger documentation page by locally or link below

- [Swagger UI Api Documentation Page](http://49.12.245.188:8087/swagger-ui/index.html)

## Author

- [@muslumcanozata](https://github.com/muslumcanozata)