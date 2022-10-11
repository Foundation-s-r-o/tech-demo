import { expect, test } from '@playwright/test'
import { incorrect_password, url, user_fullname, logoutUser, username, password } from './vars'

const error_message =
    'Neboli zadané korektné prístupové údaje.Zatvorte oznam a zadajte korektné meno a heslo.'

test.describe('On login page', () => {
    test('Failed Login', async ({ page }) => {
        await page.goto(url)

        // check
        await expect(page).toHaveURL(url + '/login')

        // find elements
        const inputUsername = page.locator('#login_username')
        const inputPassword = page.locator('#login_password')
        const btnSubmit = page.locator('#login_submit')
        const btnZatvorit = page.locator('[type="button"]')

        // interact
        await inputUsername.click()
        await inputUsername.fill(username)
        await inputPassword.click()
        await inputPassword.fill(incorrect_password)
        await btnSubmit.click()

        // check
        await expect(page).toHaveURL(url + '/login')

        // find elements
        const header_error_message = page.locator('.modal-body')

        // check
        await expect(header_error_message).toBeVisible()
        await expect(header_error_message).toContainText(error_message)
        // close error message
        await btnZatvorit.click()
    })

    test('Successful Login', async ({ page }) => {
        // Runs before each test and signs in each page.
        await page.goto(url)

        // check
        await expect(page).toHaveURL(url + '/login')

        // find elements
        const inputUsername = page.locator('#login_username')
        const inputPassword = page.locator('#login_password')
        const btnSubmit = page.locator('#login_submit')

        // interact
        await inputUsername.click()
        await inputUsername.fill(username)
        await inputPassword.click()
        await inputPassword.fill(password)
        await btnSubmit.click()

        // check
        await expect(page).toHaveURL(url)

        // find elements
        const headerUsername = page.locator('#app_header_user_fullname')

        // check
        await expect(headerUsername).toBeVisible()
        await expect(headerUsername).toContainText(user_fullname)
    })

    // this terminates session for other tests !!!!
    // we need a second user to be able to run this !!!!
    // skip this test for now
    test.skip('Successfully Logged out', async ({ page }) => {
        await page.goto(url)

        //check
        await expect(page).toHaveURL(url + '/login')

        // find elements
        const inputUsername = page.locator('#login_username')
        const inputPassword = page.locator('#login_password')
        const btnSubmit = page.locator('#login_submit')

        // interact
        await inputUsername.click()
        await inputUsername.fill(username)
        await inputPassword.click()
        await inputPassword.fill(password)
        await btnSubmit.click()

        // find elements
        const btnOdhlasit = page.locator('button.ms-2')

        // interact
        await btnOdhlasit.click()

        // find elements
        const headerUsername = page.locator('#app_header_user_fullname')

        // check
        await expect(headerUsername).toBeVisible()
        await expect(headerUsername).toContainText(logoutUser)
    })
})
