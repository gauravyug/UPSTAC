package org.upgrad.upstac.testrequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.upstac.config.security.UserLoggedInService;
import org.upgrad.upstac.users.User;

@RestController
public class TestRequestController {

    @Autowired
    private UserLoggedInService userLoggedinService;

    @Autowired
    private TestRequestRepository testRequestRepository;
    @PostMapping("/api/testrequests")
    public TestRequest createTestRequest(TestRequestInput reqInput) {
        User loggedInUser = userLoggedinService.getLoggedInUser();

    }
}
