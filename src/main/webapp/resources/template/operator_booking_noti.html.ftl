<html>
 
<head></head>
 
<body>
    <p>New booking from customer with below information</p>
    <p>Name: ${name}</p>
    <p>Phone: ${phone}</p>
    <p>Email: ${email}</p>
    <p>Date: ${date}</p>
    <p>Time: ${time}</p>
    <p>Status: ${status}</p>
    <p>Services:</p>
    <ul>
        <#list services as service>
            <li>${service}</li>
        </#list>
    </ul>
    <p>Remarks: ${question}</p>
    <p>Please manage this booking in the management portal</p>
</body>
 
</html>