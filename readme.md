## feature/avl-services-devarshi ( name to be changed)

#### POC - Devarshi Ojha - devrashi.ojha@avisbudget.com


This branch serves as master for automation framework for availability individual and aggregator services.

RateShop(RS) - https://reservation.dev.sdp.abg.cloud/service-avlrs/availability/avis/v1/car/rates/shop

RateParticular(RP) - https://reservation.dev.sdp.abg.cloud/service-avlrp/availability/avis/v1/car/rates/particular

DisplayAvailability(DA) - https://reservation.dev.sdp.abg.cloud/service-avlda/availability/avis/v1/cars

### Prerequisites
1. JDK >= 8 should be installed and JAVA_HOME should be set.
2. Maven should be installed.
3. (Optional) - IntellijIDEA, Eclipse or any other Java IDE is installed 

### Setup

In a desired directory, use terminal(mac) or command-line(windows) to get a clone of the whole repo.

```bash
git clone https://bitbucket.org/avisbudget/booking-transformation.git
```

Switch to this branch 

```bash
git checkout feature/avl-services-devarshi
```

### Execution
We can execute the tests from either command line or from any IDE -
#### 1. From Command Line

```bash
# run tests
mvn clean test
```
After the maven task is finished i.e. tests have been executed - 
To view the execution reoprt, open 'extent-report.html' located in path 'bt-api-tests/extent-reports' 

#### 2. From IDE
 
2.1.  Import the maven project\
2.2  Find the testng xml file located in 'bt-api-tests/src/test/java/testng/regression.xml'\
2.3  Right click and run as testng.\
2.4  To view the execution reoprt, open 'extent-report.html' located in path 'bt-api-tests/extent-reports' 


### Jenkins Job

You can see job execution result or run a new build depending on the access level on jenkins server - 

RS Job - https://use1xpadopecs01.aws.abg.com/view/QA%20Team/job/abg_booking_transformation/job/avl-rs-individual-regression/

RP Job - https://use1xpadopecs01.aws.abg.com/view/QA%20Team/job/abg_booking_transformation/job/avl-rp-individual-regression/

DA Job -https://use1xpadopecs01.aws.abg.com/view/QA%20Team/job/abg_booking_transformation/job/avl-da-individual-regression/

