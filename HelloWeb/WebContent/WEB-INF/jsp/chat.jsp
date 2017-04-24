<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Chatbot</title>
   </head>

   <body>
      <h2>Hi , welcome to auto booking</h2>
     			<form method="POST" action="/HelloWeb/chat">
							<div>
								<!-- <label for="message">Message</label> -->
								<textarea name="chat" placeholder="${command}" rows="20" cols="100"></textarea>
								
							</div>
							<div>
								<!-- <label for="chat"></label> -->
								<input type="text" name="message" class="form-control" placeholder="Type here">
							</div>
						
						<div class="form-group">
							<input type="submit" value="Send Message" class="btn btn-primary">
						</div>

					</form>		
   </body>
   
</html>