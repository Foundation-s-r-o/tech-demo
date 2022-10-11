import 'dotenv/config'

export const username = process.env.ADMIN_USERNAME
export const password = process.env.ADMIN_PASS
export const url = 'http://localhost:9001'
export const storageStateAdmin = './tests/storageStateAdmin.json'
export const storageStateUser = './tests/storageStateUser.json'
export const incorrect_password = 'IncorrectPassw0rd!'
export const user_fullname = 'Admin Admin'
export const logoutUser = 'Neprihlásený používateľ'
