# GAMESYS CHALENGE TEST APPLICATION

Clone to local repository as a Maven project.

Run  **ee.gamesys.testCase.TestCaseApplication.java** for start project

Run  **ee.gamesys.testCase.TestCaseApplicationTests.java** as JUnit test.

**Please pay attention that I use embeded H2 database for this test case. Every time test started it neccessary to wait some time to fill database by some data from extern free API source. That is why test demand long time, please waiting for complete.**

This waiting time adjusted by 2 constants:

**TestCaseApplication.requestDelay**

**TestCaseApplication.resultSize**

I set it as 5 and 4 for Test purposes.