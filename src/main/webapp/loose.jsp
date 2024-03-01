<jsp:useBean id="question" scope="request" type="ua.javarush.textquest.domain.Question"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Text Quest Game - You Lose</title>
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
            flex: 0;
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
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

        button {
            margin: 10px;
            padding: 10px 20px;
            background-color: #ff3b3b;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-transform: uppercase;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #e60000;
        }
    </style>
</head>
<body>
<jsp:include page="/header.jsp"/>

<div class="container">
    <h2>Game Over</h2>
    <p>${question.text}</p>
    <form action="../" method="GET">
        <button type="submit">Get another attempt</button>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>
