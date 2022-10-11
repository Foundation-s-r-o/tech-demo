import { chromium, FullConfig, expect } from '@playwright/test'
import { storageStateAdmin, url, user_fullname } from './vars'
import 'dotenv/config'

const username = process.env.ADMIN_USERNAME
const password = process.env.ADMIN_PASS

async function globalSetup(config: FullConfig) {
    const browser = await chromium.launch()
    const context = await browser.newContext()
    const page = await context.newPage()
    await page.goto(url)
    await expect(page).toHaveURL(url + '/login')
    const inputUsername = page.locator('#login_username')
    const inputPassword = page.locator('#login_password')
    const btnSubmit = page.locator('#login_submit')
    await inputUsername.click()
    await inputUsername.fill(username)
    await inputPassword.click()
    await inputPassword.fill(password)
    await btnSubmit.click()
    await expect(page).toHaveURL(url)

    const headerUsername = page.locator('#app_header_user_fullname')
    await expect(headerUsername).toBeVisible()
    await expect(headerUsername).toContainText(user_fullname)

    // Save signed-in state to 'storageState.json'.
    await page.context().storageState({ path: storageStateAdmin })
    await browser.close()
}

export default globalSetup
