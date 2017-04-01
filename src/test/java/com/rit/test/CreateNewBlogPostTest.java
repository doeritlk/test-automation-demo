package com.rit.test;

import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.rit.test.data.InMemoryDataSource;
import com.rit.test.data.User;
import com.rit.test.pages.BlogEditorPage;
import com.rit.test.pages.BlogPreviewPage;
import com.rit.test.pages.LoginPage;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.CombinableMatcher.CombinableBothMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CreateNewBlogPostTest extends WebUiTestBase {
    private final String loginUrl = "https://myfirstblog-3.ghost.io/ghost/signin/";
    private final String logsOutUrl = "https://myfirstblog-3.ghost.io/ghost/signout";
    private String aRegisteredUser;
    private String password;
    private BlogPreviewPage blogPreviewPage;

    @Before
    public void setUserLoginDetails() {
        User user = new InMemoryDataSource().read().get(1);
        aRegisteredUser = user.username();
        password = user.password();
    }

    @After
    public void logsOut() throws IOException {
        takeScreenShot();
        final LoginPage loginPage = new LoginPage(webDriver, loginUrl);
        loginPage.logsOut(logsOutUrl);
    }

    @Test
    public void canAddANewBlogPost() throws Exception {
        given(userLogsInAs(aRegisteredUser, password));

        when(userAddsANewBlogPost());
        and(entersTheHeaderOf("My First Blog Post"));
        and(theContentOf("This is my cool content"));
        and(savesThePost());

        when(thePageIsRefreshed());

        then(theContentOfBlogPost(), theHeaderEqualsTo("My First Blog Post")
                .and(theContentEqualsTo("This is my cool content"))
        );
    }


    @Test
    public void canPreviewTheBlogPost() throws Exception {
        given(userLogsInAs(aRegisteredUser, password));

        when(userAddsANewBlogPost());
        and(entersTheHeaderOf("My Second Blog Post"));
        and(theContentOf("This is my cool content"));
        and(savesThePost());

        when(clicksThePreview());

        then(theContentOfPreview(), theHeaderEqualsTo("My Second Blog Post")
                .and(theContentEqualsTo("This is my cool content"))
        );
    }

    private StateExtractor<BlogPost> theContentOfPreview() {
        return capturedInputAndOutputs1 -> new BlogPost(blogPreviewPage.header(), blogPreviewPage.content());
    }

    private ActionUnderTest clicksThePreview() {
        return (givens, inputAndOutputs) -> {
            blogPreviewPage = new BlogEditorPage(webDriver).preview();
            return inputAndOutputs;
        };
    }

    private CombinableBothMatcher<BlogPost> theHeaderEqualsTo(final String title) {
        return new CombinableBothMatcher<>(headerMatcher(title));
    }

    private StateExtractor<BlogPost> theContentOfBlogPost() {
        return capturedInputAndOutputs -> {
            BlogEditorPage blogEditorPage = new BlogEditorPage(webDriver);

            return new BlogPost(blogEditorPage.header(), blogEditorPage.content());
        };
    }

    private ActionUnderTest thePageIsRefreshed() {
        return (interestingGivens, capturedInputAndOutputs) -> {
            new BlogEditorPage(webDriver).refresh();
            return capturedInputAndOutputs;
        };
    }

    private GivensBuilder savesThePost() {
        return givens -> {
            new BlogEditorPage(webDriver).saveDraft();
            return givens;
        };
    }

    private GivensBuilder theContentOf(final String content) {
        return givens -> {
            new BlogEditorPage(webDriver).entersContent(content);
            return givens;
        };
    }

    private GivensBuilder entersTheHeaderOf(final String header) {
        return givens -> {
            new BlogEditorPage(webDriver).entersHeader(header);
            return givens;
        };
    }

    private ActionUnderTest userAddsANewBlogPost() {
        return (interestingGivens, capturedInputAndOutputs) -> {
            new BlogEditorPage(webDriver).clickNewPost();
            return capturedInputAndOutputs;
        };
    }

    private GivensBuilder userLogsInAs(final String username, final String password) {
        return givens -> {
            new LoginPage(webDriver, loginUrl).login(username, password);
            givens.add("logged in user", username);
            return givens;
        };
    }

    private Matcher<? super BlogPost> theContentEqualsTo(final String content) {
        return new TypeSafeMatcher<BlogPost>() {
            @Override
            protected boolean matchesSafely(final BlogPost blogPost) {
                return content.equals(blogPost.content());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("is " + content);
            }

            @Override
            protected void describeMismatchSafely(final BlogPost item, final Description mismatchDescription) {
                mismatchDescription.appendText("is " + item.title());
            }
        };
    }

    private Matcher<? super BlogPost> headerMatcher(final String title) {
        return new TypeSafeMatcher<BlogPost>() {
            @Override
            protected boolean matchesSafely(final BlogPost blogPost) {
                return title.contains(blogPost.title());
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("is " + title);
            }

            @Override
            protected void describeMismatchSafely(final BlogPost item, final Description mismatchDescription) {
                mismatchDescription.appendText("is " + item.title());
            }
        };
    }
}
