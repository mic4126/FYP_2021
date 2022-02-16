DELIMITER |

CREATE TRIGGER IF NOT EXISTS UserInsertTrigger BEFORE INSERT ON `user`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS UserUpdateTrigger BEFORE UPDATE ON `user`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS ProjectUserInsertTrigger BEFORE INSERT ON `Project_User`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS ProjectUserUpdateTrigger BEFORE UPDATE ON `Project_User`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;


DELIMITER |

CREATE TRIGGER IF NOT EXISTS ProjectUserDeleteTrigger BEFORE DELETE ON `Project_User`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS ProjectInsertTrigger BEFORE INSERT ON `Project`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS ProjectUpdateTrigger BEFORE UPDATE ON `Project`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS DetailInsertTrigger BEFORE INSERT ON `Detail`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS DetailUpdateTrigger BEFORE UPDATE ON `Detail`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS AttachmentInsertTrigger BEFORE INSERT ON `Attachment`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS AttachmentUpdateTrigger BEFORE UPDATE ON `Attachment`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS TagInsertTrigger BEFORE INSERT ON `Tag`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;

DELIMITER |

CREATE TRIGGER IF NOT EXISTS TagUpdateTrigger BEFORE DELETE ON `Tag`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END;
|
DELIMITER ;
