<jsp:useBean id="question" scope="request" type="ua.javarush.textquest.domain.Question"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Text Quest Game - Question</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .container {
            flex: none;
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #333;
        }

        p {
            color: #666;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        button {
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-transform: uppercase;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-transform: uppercase;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="container">
    <h2>Question ${question.id}</h2>
    <p>${question.text}</p>

    <c:forEach items="${question.answers}" var="answer">
        <form action="<c:url value="/text-quest/question"/>" method="GET">
            <button type="submit" name="nextQuestionId" value="${answer.nextQuestionId}">${answer.text}</button>
        </form>
    </c:forEach>
</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>