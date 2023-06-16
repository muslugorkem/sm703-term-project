terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.52.0"
    }
  }
  required_version = ">= 1.1.0"
}

provider "aws" {
  region = "eu-west-1"
}

resource "aws_instance" "web" {
  ami           = "ami-01dd271720c1ba44f"
  instance_type = "t2.micro"

  user_data = <<-EOF
              #!/bin/bash
              sudo amazon-linux-extras install -y java-openjdk11
              sudo wget https://term-project-jars.s3.amazonaws.com/your-app.jar -O /home/ec2-user/term-project-0.0.1-SNAPSHOT.jar
              sudo chmod +x /home/ec2-user/term-project-0.0.1-SNAPSHOT.jar
              sudo echo '@reboot root nohup java -jar /home/ec2-user/your-app.jar &' >> /etc/crontab
              sudo reboot
              EOF
}


resource "aws_s3_bucket" "bucket" {
  bucket = "term-project-jars"
  acl    = "private"

  versioning {
    enabled = true
  }
}

output "web-address" {
  value = aws_instance.web.public_dns
}
