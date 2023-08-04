package school.redrover.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.model.base.BaseMainHeaderPage;


import java.util.List;

public class ManageUsersPage extends BaseMainHeaderPage<ManageUsersPage> {

    @FindBy(xpath = "//a[@href='addUser']")
    private WebElement createUser;

    @FindBy(xpath = "//span[contains(text(), 'Configure')]")
    private WebElement configureInDropDownMenu;

    @FindBy(xpath = "//span[contains(text(), 'Delete')]")
    private WebElement deleteInDropDownMenu;

    @FindBy(xpath = "//a[@class ='jenkins-table__link model-link inside']")
    private List<WebElement> users;

    @FindBy(id = "people")
    private List<WebElement> people;

    @FindBy(xpath = "//a[contains(@href,'/delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//td//a[contains(@href, 'configure')]")
    private WebElement configureAdminButton;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public CreateUserPage clickCreateUser() {
        createUser.click();

        return new CreateUserPage(getDriver());
    }

    public String getButtonText() {
        return createUser.getText().trim();
    }

    public UserPage clickUserIDName(String userName) {
        WebElement userIDNameLink = getWait2()
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='user/" + userName + "/']")));
        userIDNameLink.click();

        return new UserPage(getDriver());
    }

    public ManageUsersPage openUserIDDropDownMenu(String userName) {
        getDriver()
                .findElement(By.xpath("//a[@href='user/" + userName + "/']/button[@class='jenkins-menu-dropdown-chevron']"))
                .sendKeys(Keys.ENTER);

        return this;
    }

    public ManageUsersPage selectConfigureUserIDDropDownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(configureInDropDownMenu)).click();

        return this;
    }

    public DeletePage<MainPage> selectDeleteUserInDropDownMenu() {
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteInDropDownMenu)).click();

        return new DeletePage<>(new MainPage(getDriver()));
    }

    public boolean isUserExist(String userName) {
        for (WebElement el : users) {
            if (el.getText().equals(userName)) {

                return true;
            }
        }

        return false;
    }

    public DeletePage<ManageUsersPage> clickDeleteUser() {
        deleteButton.click();

        return new DeletePage<>(this);
    }

    public boolean getUserDeleted(String username) {
        for (WebElement user : people) {
            if (user.getText().equals(username)) {
                break;
            }
        }
        return false;
    }

    public UserConfigPage clickFirstUserEditButton() {
        configureAdminButton.click();

        return new UserConfigPage(new UserPage(getDriver()));
    }

    public UserConfigPage clickUserEditButton(String userName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td//a[contains(@href, '" + userName + "/configure')]"))).click();

        return new UserConfigPage(new UserPage(getDriver()));
    }

    public UserPage selectConfigureButton(String newUserName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='user/" + newUserName + "/configure']"))).click();

        return new UserPage(getDriver());
    }
}

