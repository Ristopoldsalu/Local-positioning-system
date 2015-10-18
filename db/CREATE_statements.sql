-- Version 0.3.0
-- @TeamGREEN - loogilised muutused

CREATE TABLE Building
(
building_id SERIAL PRIMARY KEY,
building_name TEXT NOT NULL
);

CREATE TABLE Floor
(
floor_id SERIAL PRIMARY KEY,
floor_name TEXT,
floor_number INT NOT NULL,
building_id INT NOT NULL REFERENCES Building (building_id)
);

CREATE TABLE RoomType
(
  roomtype_id SERIAL PRIMARY KEY,
  roomtype_name TEXT NOT NULL
);

CREATE TABLE Room
(
room_id SERIAL PRIMARY KEY,
room_name TEXT NOT NULL,
roomtype_id INT NOT NULL,
room_number TEXT NOT NULL,
floor_id INT NOT NULL REFERENCES Floor (floor_id)
);

CREATE TABLE PathPoint
(
pathpoint_id SERIAL PRIMARY KEY,
pathpoint_location POINT NOT NULL,
pathpoint_name TEXT NOT NULL
);


CREATE TABLE Beacon
(
  beacon_id SERIAL PRIMARY KEY,
  beacon_macaddr TEXT UNIQUE NOT NULL,
  corridor_pathpoint_id INT NOT NULL REFERENCES PathPoint (pathpoint_id),
  room_id INT NOT NULL REFERENCES Room (room_id)
);

CREATE TABLE Path
(
path_id SERIAL PRIMARY KEY,
pathpoint_id_start INT NOT NULL REFERENCES PathPoint (pathpoint_id),
pathpoint_id_end INT NOT NULL REFERENCES PathPoint (pathpoint_id),
path_handycap BOOLEAN NOT NULL
);

CREATE TABLE UserType
(
user_id SERIAL PRIMARY KEY,
user_type TEXT NOT NULL,
user_multiplier INT NOT NULL
);

CREATE TABLE Cost
(
cost_id SERIAL PRIMARY KEY,
cost_value INT NOT NULL
);
