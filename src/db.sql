-- tbl_ploj_user
CREATE TABLE `tbl_ploj_user` (
  `userid` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `bitcode` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;