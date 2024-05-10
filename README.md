# Phone Booking System

## Overview

The **Phone Booking System** is a standalone application for managing phone bookings. This README provides instructions for downloading the source code from GitHub, building the package using Maven, and creating a Docker image to run the application in interactive mode.

## Prerequisites

Before proceeding, ensure you have the following installed on your system:
- Git
- Maven
- Docker

## Download Source Code

To download the source code from GitHub, use the following command:
git clone 

## Build Package

Navigate to the root directory of the downloaded source code and use Maven to build the package:

cd PhoneBookingSystem
mvn clean package

This command will compile the source code, run tests, and package the application into a JAR file.

## Create Docker Image

After successfully building the package, you can create a Docker image:

docker build -t pbs .

This command will create a Docker image named pbs using the Dockerfile provided in the repository.

## Run in Interactive Mode

Once the Docker image is created, you can run the application in interactive mode:

docker run -i -t pbs

This command will start the application within a Docker container, allowing you to interact with it via the terminal.

## Usage

When the application starts, it will display all the available phones in the system and you will be presented with a menu:
- Enter 1 to Book a phone
- Enter 2 to Return a phone
- Enter 3 to Enter model name to check its availability and details
- Enter 4 to Check availability and details of all the phones in the system
- Enter 5 to Exit

Choose the appropriate option and follow the prompts to interact with the application.

**Note:** This application is a standalone system and does not require any external dependencies once packaged into a Docker image. It can be easily deployed and run on any system with Docker installed.
