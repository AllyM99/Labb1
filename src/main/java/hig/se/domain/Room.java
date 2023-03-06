package hig.se.domain;

import javax.xml.namespace.QName;

public class Room {

    int id;
    int roomNumber;
    int floor;
    RoomType roomType;

    public Room(int id, int roomNumber, RoomType roomType,int floor) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.floor = floor;

    }
    public Room(int roomNumber, RoomType roomType,int floor) {
        setRoomNumber(roomNumber);
        setRoomType(roomType);
        setFloor(floor);
    }
    public int getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setFloor(int floor) {
        if (floor <= 0 || floor > 99){
            throw new IllegalArgumentException("VART TROR DU ATT DU ÄR HA?");
        }
        this.floor = floor;
    }
    public int getFloor(){
        return floor;
    }

    public void setRoomNumber(int roomNumber) {
        if (roomNumber <= 0 || roomNumber > 499){
            throw new IllegalArgumentException("Room number must be greater than 0!");
        }
        this.roomNumber = roomNumber;
    }

}
