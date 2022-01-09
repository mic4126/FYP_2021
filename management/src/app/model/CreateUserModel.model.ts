export type CreateUserModel = {
    username:string;
    firstName:string;
    lastName:string;
    email:string;
    userType:['admin'|'dev']
}