#include <opencv2/core.hpp>
#include <opencv2/videoio.hpp>
#include <opencv2/highgui.hpp>
#include <iostream>
#include <stdio.h>

using namespace cv;
using namespace std;

int main(int, char**)
{

    //--- INITIALIZE VIDEOCAPTURE
    VideoCapture cap;
    // open the default camera using default API
    // cap.open(0);
    // OR advance usage: select any API backend
    int deviceID = 0; // 0 = open default camera
    int apiID = cv::CAP_ANY; // 0 = autodetect default API
    //int apiID = cv::CAP_V4L2; // 0 = autodetect default API
    // open selected camera using selected API
    cap.open(deviceID, apiID);
    // check if we succeeded
    if (!cap.isOpened()) {
    cerr << "ERROR! Unable to open camera\n";
    return -1;
    }

    //--- GRAB AND WRITE LOOP
    cout << "Start grabbing" << endl
    << "Press any key to terminate" << endl;
    while(true)
    {
        Mat frame;
        // wait for a new frame from camera and store it into 'frame'
        bool ret = cap.read(frame);
        //frame.convertTo(frame, CV_32FC3);
        //normalize(frame, frame, 0.0f, 1.0f, NORM_MINMAX);

        /*int cols = frame.cols, rows = frame.rows;
        for(int i = 0; i < rows; i++)
        {
        const double* Mi = M.ptr<double>(i);
        for(int j = 0; j < cols; j++)
        sum += std::max(Mi[j], 0.);
        }*/

        // compute the sum of positive matrix elements, optimized variant
        /*double sum=0;
        double maximale_value = 0;
        int cols = frame.cols, rows = frame.rows;
        if(frame.isContinuous())
        {
        cols *= rows;
        rows = 1;
        }
        for(int i = 0; i < rows; i++)
        {
        const double* Mi = frame.ptr<double>(i);
        for(int j = 0; j < cols; j++)
        {
        //sum += std::max(Mi[j], 0.);
        if( std::max(Mi[j], 0.) > maximale_value )
            maximale_value = std::max(Mi[j], 0.);
        }
        }*/

        //cout << frame.at<int>(100,100) << endl; //<< maximale_value << endl;
        //normalize(frame, frame, 1,0);
        // check if we succeeded
        //if (ret == true)
        {
            if (frame.empty()) {
                cerr << "ERROR! blank frame grabbed\n";
                break;
            }
            // show live and wait for a key with timeout long enough to show images
            imshow("Live", frame);
            wait();
            //if (waitKey(0) >= 0)
            //break;
        }
    }
    // the camera will be deinitialized automatically in VideoCapture destructor
    return 0;
}
