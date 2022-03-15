export type Notice = {
    type: NOTICE_TYPE_ENUM;
    message: string;
    timeout?: number;

}

export enum NOTICE_TYPE_ENUM {
    "SUCCESS" = "alert-success",
    "ERROR" = "alert-danger",    
}