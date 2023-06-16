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

data "aws_security_group" "existing" {
  name = "sg"
}

resource "aws_security_group" "sg" {
  name        = "sg"
  description = "Allow inbound traffic"
  count  = length(data.aws_s3_bucket.existing) > 0 ? 0 : 1


  ingress {
    description = "Allow incoming traffic on port 8080"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "web" {
  ami                    = "ami-01dd271720c1ba44f"
  instance_type          = "t2.micro"
  vpc_security_group_ids = aws_security_group.sg != [] ? [aws_security_group.sg[0].id] : []

  user_data = <<-EOF
              #!/bin/bash
              sudo amazon-linux-extras install -y java-openjdk11
              sudo wget https://term-project-jars.s3.amazonaws.com/term-project-0.0.1-SNAPSHOT.jar -O /home/ec2-user/term-project-0.0.1-SNAPSHOT.jar
              sudo chmod +x /home/ec2-user/term-project-0.0.1-SNAPSHOT.jar
              sudo echo '@reboot root nohup java -jar /home/ec2-user/term-project-0.0.1-SNAPSHOT.jar &' >> /etc/crontab
              sudo reboot
              EOF
}


data "aws_s3_bucket" "existing" {
  bucket = "term-project-jars"
}

resource "aws_s3_bucket" "bucket" {
  count  = data.aws_s3_bucket.existing ? 0 : 1
  bucket = "term-project-jars"
  acl    = "private"

  versioning {
    enabled = true
  }
}

output "web-address" {
  value = aws_instance.web.public_dns
}
