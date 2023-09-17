import socket 
import os
import threading

import time

from PIL import Image, ImageDraw, ImageFont

from luma.core.interface.serial import i2c
from luma.core.render import canvas
from luma.oled.device import ssd1306, ssd1325, ssd1331, sh1106
import time 
#import spidev #raspberry pi
import RPi.GPIO as gpio


import sys
sys.path.append("/home/pi/Desktop/PI")
import camera


Nanumfont = ImageFont.truetype("NanumSquareRoundB.ttf", 20)

gpio.setwarnings(False)

gpio.setmode(gpio.BCM)

trig=13
echo=19

gpio.setup(trig,gpio.OUT)
gpio.setup(echo,gpio.IN)

gpio.setup(20,gpio.OUT)
gpio.setup(21,gpio.OUT)
#spi=spidev.SpiDev() 
#spi.open(0,0) 

serial=i2c(port=1,address=0x3C)
device=ssd1306(serial)
device=ssd1306(serial,rotate=0)

HOST = "172.20.10.7" 

PORT = 8888

BUFSIZE = 1024 #버퍼사이즈 설정

MAX_BUF = 1024

global data

"""
def led_on(led_pin):
    gpio.setmode(gpio.BCM)
    gpio.setup(led_pin,gpio.out)
    gpio.ouput(pin,True)
    time.sleep(1)
    gpio.cleanup(led_pin)
    
def led_off(led_pin):
    gpio.setmode(gpio.BCM)
    gpio.setup(led_pin,gpio.out)
    gipo.cleanup(led_pin)
"""    

def send():
    
    while True:
        global data
        #data="empty"
        client_socket.send(data.encode("utf-8"))  #클라이언트에게 데이터를 전송
        time.sleep(0.1)

    client_soket.close()


def receive():
    global data

    while True:
        recv_data = client_socket.recv(BUFSIZE)
        data=recv_data.decode("utf-8")
        #print("idfa",data,"er")
        #print(type(data))
        """
        with canvas(device) as draw:
            draw.rectangle(device.bounding_box,outline="white",fill="black") #backgraoud
            draw.text((50,25),"full",fill=data)
        """
    client_socket.close()
    
    
def sensor():
    global data
    start_time=0
    
    while 1:
        try:
            #distance=0
            #mytime=0
            gpio.output(trig,False)
            time.sleep(0.5)
            
            gpio.output(trig, True)
            time.sleep(0.00001)
            gpio.output(trig,False)
                
            while gpio.input(echo)==0:
                pulse_start=time.time()
                
            while gpio.input(echo)==1:
                pulse_end=time.time()
                    
            pulse_duration=pulse_end-pulse_start
            distance=pulse_duration*17000
            distance=round(distance,2)
            distance=float(distance)
            
            print(distance)
            

            
            #print(data)
            #print(type(data))
            #if "connect".find(data)==0:
            print(data)
            
            current_time=time.time()
            if current_time-start_time<10000000:
                print("wait time: ",current_time-start_time)
                
                
                if current_time-start_time>=20:
                    #gpio.output(20,False)
                    #gpio.cleanup()
                    gpio.setup(20,gpio.OUT)
                    gpio.output(20,False)
                    gpio.setup(21,gpio.OUT)
                    gpio.output(21,False)
                    data="empty"
                    start_time=0
                
            
            if (data!="wait")and(data!="full")and(data!="empty")and(data!="connect"):
                if "connect" in data:
                    print(data)
                    
                else:
                    print(data)
                    if start_time==0:
                        start_time=time.time()
                    with canvas(device) as draw:
                        draw.rectangle(device.bounding_box,outline="white",fill="black") #backgraoud
                        draw.text((10,25),data,fill="white", font = Nanumfont,)

                    if distance<20:
                        time.sleep(1)
                        char=camera.camera("full")
                        print("camera:",char)
                        print("data:",type(data),len(data))
                        print("camera:",type(char),len(char))
                        #print(type.char)
                        #print(type.data)
                        if (data in char) or (char in data): 
                            #print(char)
                            data="full"
                            start_time=0
                            print("state",data)
                            gpio.output(20,gpio.OUT)
                            gpio.setup(20,False)
                            gpio.output(21,True)
                            time.sleep(1)
                            gpio.setup(21,gpio.OUT)
                            gpio.output(21,False)
                            #gpio,cleanup()
                            #continue
                        else:    
                            print("error")
                            gpio.output(20,True)
                            #gpio.setup(20,gpio.out)
                            #time.sleep(1)
                            continue
                        
                            
                    else:
                        gpio.setup(20,gpio.OUT)
                        gpio.output(20,False)
                        #gpio.cleanup()
                        #led_off(20)
                        continue   
                        #print(data)
                        
                        
            #print(distance)
            
                   
            if distance<22:
                data="full"
                with canvas(device) as draw:
                    draw.rectangle(device.bounding_box,outline="white",fill="black") #backgraoud
                    draw.text((50,25),"full",fill="white")
                #print(distance)
            elif distance>27:
                data="empty"
                with canvas(device) as draw:
                    draw.rectangle(device.bounding_box,outline="white",fill="black") #backgraoud
                    draw.text((50,25),"empty",fill="white")
                    #print(distance)
            elif 22<=distance<=27:
                data="wait"
                with canvas(device) as draw:
                    draw.rectangle(device.bounding_box,outline="white",fill="black") #backgraoud
                    draw.text((50,25),"wait",fill="white")

        except:
            gpio.cleanup()
            

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST,PORT))
server_socket.listen(1)

while 1:
    try:
        #global data
        data="empty"
        gpio.setup(20,gpio.OUT)
        gpio.output(20,False)
        gpio.setup(21,gpio.OUT)
        gpio.output(21,False)

        sensor_thread = threading.Thread(target = sensor)
        sensor_thread.start()
        
        
        client_socket, addr = server_socket.accept()

        print("Connected by", addr)
        
        recv_thread = threading.Thread(target = receive)
        send_thread = threading.Thread(target = send)


        recv_thread.start()
        send_thread.start()
        
        

        
    except:
        time.sleep(0.1)
        


                         



