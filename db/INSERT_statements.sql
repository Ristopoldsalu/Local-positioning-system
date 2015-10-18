INSERT INTO building (building_id, building_name)
    VALUES
      (1 ,'Tammsaare ärikeskus'),
      (2 ,'Rocca Al Mare keskus'),
      (3 ,'Ülemiste keskus'),
      (4 ,'Tieto Vilnius');

INSERT INTO floor (floor_id, floor_name, floor_number, building_id)
    VALUES
      (1, '6th Floor', 6, 1),
      (2, '3rd Floor', 7, 1),
      (3, '7th Floor', 1, 2),
      (4, '1st Floor', 1, 3),
      (5, 'Unknown floor', 1, 4);

INSERT INTO RoomType(
  roomtype_id, roomtype_name)
VALUES (1, 'room'),
  (2, 'hallway');

INSERT INTO room (
room_id, room_name, room_number, floor_id, roomtype_id)
VALUES
(1, 'Ehis', '1', 5, 1),
(2, 'Star', '3', 5, 1),
(3, 'Skais', '5', 5, 1),
(4, 'Ants', '7', 5, 1),
(5, 'Anneli', '9', 5, 1),
(6, 'Laura', '12', 5, 1),
(7, 'BS', '14', 5, 1),
(8, 'Tallink', '15', 5, 1);


INSERT INTO PathPoint(
  pathpoint_id, pathpoint_location, pathpoint_name) VALUES
  (1,'(3410,231)', 'Ehis'),
  (2, '(3065,220)', 'Star'),
  (3, '(1842,204)', 'Skais'),
  (4, '(1013,203)', 'Ants'),
  (5, '(3801,244)', 'Anneli'),
  (6, '(2728,268)', 'Laura'),
  (7, '(2316,269)', 'BS'),
  (8, '(1376,261)', 'Tallink');

INSERT INTO beacon(
beacon_id, beacon_macaddr, room_id, corridor_pathpoint_id)
VALUES (1,'20:15:03:17:41:89', 1, 1),
(2,'20:15:05:05:07:61', 2, 2),
(3,'20:15:03:02:58:55', 3, 3),
(4,'20:15:03:17:02:50', 4, 4),
(5,'20:15:03:17:38:37', 5, 5),
(6,'20:15:03:17:41:92', 6, 6),
(7,'20:15:05:05:06:45', 7, 7),
(8,'20:15:04:30:75:02',  8, 8);


INSERT INTO path (path_id, pathpoint_id_start, pathpoint_id_end, path_handycap)
    VALUES
(1,4,8,TRUE),
(2,8,3,FALSE),
(3,3,7,TRUE),
(4,7,6,TRUE),
(5,6,2,TRUE),
(6,2,1,TRUE),
(7,1,5,TRUE);
