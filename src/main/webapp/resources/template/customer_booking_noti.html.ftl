<html>
 
<head></head>
 
<body>
    <p>Dear ${name},</p>
    <p>Thank you for choosing our service, please see your booking details as the following</p>
    <p>Name: ${name}</p>
    <p>Phone: ${phone}</p>
    <p>Email: ${email}</p>
    <p>Date: ${date}</p>
    <p>Time: ${time}</p>
    <p>Services:</p>
    <ul>
        <#list services as service>
            <li>${service}</li>
        </#list>
    </ul>
    <p>Remarks: ${question}</p>
    <p>We will contact you soon to reconfirm about your booking.</p>
    <p>Yours sincerely,</p>
    <p>Majestic Nails and Spa</p>
</body>
 
</html>