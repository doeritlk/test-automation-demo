package com.doeritlk.test.user;

import com.doeritlk.test.WebUiTestBase;
import com.doeritlk.test.pages.SignUpPage;
import com.googlecode.yatspec.junit.Row;
import com.googlecode.yatspec.junit.Table;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;

public class UserSignUpTest extends WebUiTestBase {

    private SignUpPage signUpPage;

    @Test
    @Table({
            @Row({"John Flower", "johnflower30@gmail.com", "jflower$1"}),
            @Row({"Alex Marsh", "alexmarsh10@gmail.com", "alexmarsh@1"})
    })
    public void aUserCanSuccessfullySignUp(String fullname, String email, String password) throws Exception {
        given(userNavigatesToSignUpPage());
        and(theUserEntersFullName(fullname));
        and(theUserEntersEmail(email));
        and(theUserEntersPassword(password));

        when(theUserClicksRegister());

        then(theAccountHeader(), is(fullname));
    }

    private GivensBuilder theUserEntersFullName(final String fullname) {
        return givens -> {
            signUpPage.enterFullName(fullname);
            return givens;
        };
    }

    private GivensBuilder theUserEntersEmail(final String email) {
        return givens -> {
            signUpPage.enterEmail(email);
            return givens;
        };
    }

    private GivensBuilder theUserEntersPassword(final String password) {
        return givens -> {
            signUpPage.password(password);
            return givens;
        };
    }

    private StateExtractor<String> theAccountHeader() {
        return capturedInputAndOutputs -> signUpPage.pageHeader();
    }

    private ActionUnderTest theUserClicksRegister() {
        return (interestingGivens, capturedInputAndOutputs) -> {
//            capturedInputAndOutputs.add(aRequest("Open", "user", "ghost.org"), "");
//            capturedInputAndOutputs.add(aResponse("Loaded", "ghost.org", "user"), "");
            signUpPage.register();
            return capturedInputAndOutputs;
        };
    }

    private GivensBuilder userNavigatesToSignUpPage() {
        return givens -> {
            signUpPage = new SignUpPage(webDriver, "https://auth.ghost.org/signup/");
            return givens;
        };
    }

    @After
    public void takesScreenShot() throws IOException {
        takeScreenShot();
    }

}
