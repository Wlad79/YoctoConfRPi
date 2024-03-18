//#include <QThread>

// #include "opencv2/objdetect.hpp"
// #include "opencv2/highgui.hpp"
// #include "opencv2/imgproc.hpp"

#include<opencv2/opencv.hpp>//OpenCV header to use VideoCapture class and VideoWriter//

#include "camera.h"
#include <iostream>

using namespace std;
using namespace cv;

void Camera::start()
{
    // https://stackoverflow.com/questions/11537585/where-can-i-find-haar-cascades-xml-files
    // CascadeClassifier faceDetection;
    // if(!faceDetection.load("/home/nstg/opencv_build/opencv/data/haarcascades/haarcascade_frontalface_default.xml")){
    //     cout << "XML file not loaded" << endl;
    //     exit(0);
    // }
    // VideoCapture cap(0);

    // string winName = "WebCamRecord";
    // namedWindow(winName);

    // while(true){
    //     Mat img;
    //     bool success = cap.read(img);
    //     if (success == false){
    //         cout << "Video camera is disconnected" << endl;
    //         cin.get(); //Wait for any key press
    //         break;
    //     }
        // 	// vector<Rect> faces;
        // 	// faceDetection.detectMultiScale(img, faces); //detecting faces

        // 	// for(int i=0; i<faces.size(); i++){
        // 	// 	Point p1(faces[i].x, faces[i].y);
        // 	// 	Point p2((faces[i].x + faces[i].height), (faces[i].y + faces[i].width));
        // 	// 	rectangle(img, p1, p2, Scalar(0, 0, 255), 2, 8, 0);
        // 	// }

        // 	imshow("WebCamRecord", img);

    //     if (waitKey(10) == 27){
    //         cout << "Esc key is pressed by user. Stoppig the video" << endl;
    //         break;
    //     }
    // }

    Mat myImage;//Declaring a matrix to store the frames//
   VideoCapture cap(0);//Taking an object of VideoCapture Class to capture video from default camera//
   namedWindow("Video Player");//Declaring the video to show the video//
   if(!cap.isOpened()){ //This section prompt an error message if no video stream is found//
      cout << "Failed to access the camera" << endl;
      system("pause");
      return;//-1;
   }
   int frame_width = cap.get(CAP_PROP_FRAME_WIDTH);//Getting the frame height//
   int frame_height = cap.get(CAP_PROP_FRAME_HEIGHT);//Getting the frame width//
   VideoWriter video("video1.mp4",10,17,Size(frame_width, frame_height));//Declaring an object of VideoWriter class//
   while (true){ //Taking an everlasting loop to show the video//
      cap >> myImage;
      if (myImage.empty()){ //Breaking the loop if no video frame is detected//
         break;
      }
      video.write(myImage);//Write the video//
      imshow("Video Player", myImage);//Showing the video//
      char c= (char)waitKey(25);
      if(c==27){
         break;
      }
   }
   cap.release();//Releasing the buffer memory//
   video.release();
}
