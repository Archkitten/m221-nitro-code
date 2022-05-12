{% include nav.html %}

# Nitro Code Deployment Guide

## Initial Setup

### Resources Needed
* Raspberry Pi 3 or higher
* HTMI Cable
* External Monitor
* Keyboard and Mouse
* Ethernet Cable or WiFi USB

### Step 1: Raspberry Pi Necessary Packages
**Installing Java Development Kit and Maven**\
`sudo apt install default-jdk`\
`sudo apt install maven`

### Step 2: Import Project from GitHub
`cd dev`\
`git clone (repository link)`\
Repository Link is gotten from **GitHub Main Page > Code > HTTPS > Copy to clipboard**\
Example: `git clone https://github.com/Archkitten/m22p1-weenies.git`

### Step 3: Maven Build
`cd (repository name)`\
`mvn clean install`\
This will create a .jar file, usually located in **/(repository name)/target/(filename).jar**\
The file path will be used in Step 4.\
Running the project manually, this is optional and only for testing if the import succeeded or not: `mvn spring-boot:run`

### Step 4: Creating a service file for the project
**This is like making a script that runs the project for you, instead of manually running it each time.**\
`cd /etc/systemd/system`\
`sudo nano (service name, probably your repository name).service`\
Type or copy-paste into (service name).service:
```
[Unit]
Description=Java
After=network.target

[Service]
User=(system administrator)
Restart=always
ExecStart=java -jar (file path of .jar file)

[Install]
WantedBy=multi-user.target
```
Example of (system administrator): `pi`\
Example of (file path of .jar file): `/home/pi/dev/m22p1-weenies/target/csa-0.0.1-SNAPSHOT.jar`

### Step 5: Enable service file
**systemctl enable will allow the service file to run upon startup, in case the Raspberry Pi ever goes down.**\
`sudo systemctl start (service name)`\
Checking to see if your service succeeded: `systemctl status (service name)`\
If service failed: `systemctl daemon-reload` `sudo systemctl stop (service name)` Then fix the error and try again.\
`sudo systemctl enable (service name)`

### Step 6: Nginx, Optional
**Normally your service file uses tomcat to startup, but Nginx is more stable so...**\
`cd /etc/nginx/sites-available`\
`sudo nano (nginx file, probably same as service name)`\
Type or copy-paste into (nginx file):
```
server {
    listen 80;
    server_name csa.(repository name).cf;

    location / {
        proxy_pass http://localhost:8080;
    }
}
```
Example of (repository name): `weenies`\
Check to make sure file has no errors:\
`sudo ln -s /etc/nginx/sites-available/(service name) /etc/nginx/sites-enabled`\
`sudo nginx -t`\
If there are no errors, restart Nginx:\
`sudo systemctl restart nginx`

### Step 7: Port Forwarding
**The process is different for everyone depending on router type.**
1. Enter Router IP address in search bar, then log in
1. Go to port forwarding section
1. Map to port 8080

### Step 8: VNC, Optional
**This has nothing to do with the project, but will allow you to remotely connect to your Raspberry Pi without needing an external monitor and keyboard in the future.**
1. Start VNC Server on your Raspberry Pi
1. Install VNC Viewer on whatever device you wish
1. Create a VNC account, connect by using the account credentials of the Raspberry Pi

## Updating Project

### Resources Needed
* Raspberry Pi 3 or higher
* VNC Viewer
* Ethernet Cable or WiFi USB

### Manual Updating:
`sudo systemctl disable (service name)`\
`sudo systemctl stop (service name)`\
`cd ~/dev/(repository name)`\
`git pull`\
`mvn clean install`\
`sudo systemctl start (service name)`\
`sudo systemctl enable (service name)`

### Script:
```
#!/bin/bash
sudo systemctl disable (service name)
sudo systemctl stop (service name)
cd ~/dev/(repository name)
git pull
mvn clean install
sudo systemctl start (service name)
sudo systemctl enable (service name)
```
Make script executable:\
`sudo chmod +x (script filename).sh`