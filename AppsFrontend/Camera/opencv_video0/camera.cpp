//Uncomment the following line if you are compiling this code in Visual Studio
//#include "stdafx.h"

#include <opencv2/opencv.hpp>
#include <iostream>
#include "camera.h"

using namespace cv;
using namespace std;

void Camera::start()
{

     cout << getBuildInformation() << endl;
     //Open the default video camera
     cv::VideoCapture cap(0);

 // if not success, exit program
 if (cap.isOpened() == false)
 {
  cout << "Cannot open the video camera" << endl;
  cin.get(); //wait for any key press
  return;
 }

 cap.set(CAP_PROP_FRAME_WIDTH,1280);
 cap.set(CAP_PROP_FRAME_HEIGHT,720);

 double dWidth = cap.get(CAP_PROP_FRAME_WIDTH); //get the width of frames of the video
 double dHeight = cap.get(CAP_PROP_FRAME_HEIGHT); //get the height of frames of the video

 cout << "Resolution of the video : " << dWidth << " x " << dHeight << endl;

 string window_name = "My Camera Feed";
 namedWindow(window_name); //create a window called "My Camera Feed"

 VideoWriter video("/home/root/video0.mp4",10,17,Size(dWidth, dHeight));//Declaring an object of VideoWriter class//
 Mat frame;

 while (true)
 {

  //bool bSuccess = cap.read(frame); // read a new frame from video
  cap >> frame;
  if (frame.empty()){ //Breaking the loop if no video frame is detected//
    break;
  }

  //Breaking the while loop if the frames cannot be captured
  /*if (bSuccess == false)
  {
   cout << "Video camera is disconnected" << endl;
   cin.get(); //Wait for any key press
   break;
  }*/

  video.write(frame);//Write the video//

  //show the frame in the created window
  imshow(window_name, frame/255);

  //wait for for 10 ms until any key is pressed.
  //If the 'Esc' key is pressed, break the while loop.
  //If the any other key is pressed, continue the loop
  //If any key is not pressed withing 10 ms, continue the loop
  if (waitKey(10) == 27)
  {
   cout << "Esc key is pressed by user. Stoppig the video" << endl;
   break;
  }
 }
 cap.release();//Releasing the buffer memory//
 video.release();

 return;

}
