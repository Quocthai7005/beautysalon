<html>
 
<head></head>
 
<body>
    <p>New booking from customer with below information</p>
    <p>name: ${name}</p>
    <p>phone: ${phone}</p>
    <p>email: ${email}</p>
    <p>date: ${date}</p>
    <p>time: ${time}</p>
    <p>services:</p>
    <ul>
        <#list services as service>
            <li>${service}</li>
        </#list>
    </ul>
    <p>question: ${question}</p>
    <p>Please manage this booking in the management portal</p>
</body>
 
</html>