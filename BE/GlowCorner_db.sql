CREATE DATABASE GlowCorner;
GO
USE GlowCorner;
GO

-- Role entity
CREATE TABLE Role (
    roleID INT PRIMARY KEY,
    roleName NVARCHAR(255),
    description NVARCHAR(255)
);
GO
-- User entity
CREATE TABLE [User] (
    userID INT PRIMARY KEY,
    fullName NVARCHAR(255),
    email NVARCHAR(255),
    phone NVARCHAR(255),
    address NVARCHAR(255),
    skinType NVARCHAR(255),
    loyaltyPoints INT,
    roleID INT UNIQUE,
    FOREIGN KEY (roleID) REFERENCES Role(roleID)
);
GO
-- Authentication entity (1-1 with User)
CREATE TABLE Authentication (
    authenticationTokenID INT PRIMARY KEY,
    userID INT UNIQUE,
    username NVARCHAR(255),
    passwordHash NVARCHAR(255),
    FOREIGN KEY (userID) REFERENCES [User](userID)
);
GO
-- TestResult entity
CREATE TABLE TestResult (
    resultID INT PRIMARY KEY,
    userID INT UNIQUE,
    testDate DATE,
    testResult NVARCHAR(255),
    FOREIGN KEY (userID) REFERENCES [User](userID)
);
GO
-- Quiz entity
CREATE TABLE Quiz (
    quizID INT PRIMARY KEY,
    quizText NVARCHAR(255)
);
GO
-- AnswerOption entity (1-1 with TestResult, N-N with Quiz)
CREATE TABLE AnswerOption (
    optionID INT PRIMARY KEY,
    questionID INT,
    optionText NVARCHAR(255),
    FOREIGN KEY (questionID) REFERENCES Quiz(quizID)
);
GO
-- Relationship between TestResult and AnswerOption (1-1)
ALTER TABLE TestResult ADD answerOptionID INT UNIQUE;
ALTER TABLE TestResult ADD FOREIGN KEY (answerOptionID) REFERENCES AnswerOption(optionID);
GO
-- SkinCareRoutine entity
CREATE TABLE SkinCareRoutine (
    routineID INT PRIMARY KEY,
    skinType NVARCHAR(255),
    routineName NVARCHAR(255),
    description NVARCHAR(255)
);
GO
-- User and SkinCareRoutine (N-N relationship)
CREATE TABLE UserSkinCareRoutine (
    userID INT,
    routineID INT,
    PRIMARY KEY (userID, routineID),
    FOREIGN KEY (userID) REFERENCES [User](userID),
    FOREIGN KEY (routineID) REFERENCES SkinCareRoutine(routineID)
);
GO
-- Product entity
CREATE TABLE Product (
    productID INT PRIMARY KEY,
    productName NVARCHAR(255),
    description NVARCHAR(255),
    price BIGINT,
    category NVARCHAR(255),
    skinTypeCompability NVARCHAR(255),
    rating FLOAT CHECK (rating BETWEEN 1 AND 5)
);
GO
-- SkinCareRoutine and Product (N-N relationship)
CREATE TABLE SkinCareRoutineProduct (
    routineID INT,
    productID INT,
    PRIMARY KEY (routineID, productID),
    FOREIGN KEY (routineID) REFERENCES SkinCareRoutine(routineID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);
GO
-- Feedback entity
CREATE TABLE Feedback (
    feedbackID INT PRIMARY KEY,
    customerID INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment NVARCHAR(255),
    feedbackDate DATE,
    FOREIGN KEY (customerID) REFERENCES [User](userID)
);
GO
-- Order entity
CREATE TABLE [Order] (
    orderID INT PRIMARY KEY,
    customerID INT,
    orderDate DATE,
    status NVARCHAR(255),
    totalAmount INT,
    FOREIGN KEY (customerID) REFERENCES [User](userID)
);
GO
-- OrderDetail entity
CREATE TABLE OrderDetail (
    orderID INT,
    productID INT,
    quantity INT,
    price INT,
    PRIMARY KEY (orderID, productID),
    FOREIGN KEY (orderID) REFERENCES [Order](orderID),
    FOREIGN KEY (productID) REFERENCES Product(productID)
);
GO
-- Promotion entity
CREATE TABLE Promotion (
    promotionID INT PRIMARY KEY,
    promotionName NVARCHAR(255),
    discount INT,
    startDate DATE,
    endDate DATE
);
GO
-- Promotion and Order (N-N relationship)
CREATE TABLE OrderPromotion (
    orderID INT,
    promotionID INT,
    PRIMARY KEY (orderID, promotionID),
    FOREIGN KEY (orderID) REFERENCES [Order](orderID),
    FOREIGN KEY (promotionID) REFERENCES Promotion(promotionID)
);
GO

CREATE TABLE Cart (
    UserID INT PRIMARY KEY,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (UserID) REFERENCES [User](UserID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);
GO
-- Applying Promotion to Order Total Amount
CREATE TRIGGER ApplyPromotion
ON OrderPromotion
AFTER INSERT
AS
BEGIN
    UPDATE o
    SET o.totalAmount = o.totalAmount - (o.totalAmount * p.discount / 100)
    FROM [Order] o
    JOIN inserted i ON o.orderID = i.orderID
    JOIN Promotion p ON i.promotionID = p.promotionID
    WHERE p.startDate <= GETDATE() AND p.endDate >= GETDATE();
END;
