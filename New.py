import smtplib

fromaddr = 'uremail'
toaddrs  = 'users email id'
msg = 'Intrusion Detected!!!!!!!!!  Check your Dropbox'



username = 'uremail'
password = 'password'

# The actual mail send
server = smtplib.SMTP('smtp.gmail.com:587')
server.starttls()
server.login(username,password)
server.sendmail(fromaddr, toaddrs, msg)
server.quit()
print ("email sent")