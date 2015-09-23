#include <jni.h>

#include "com_echo_EchoServerActivity.h"
#include "com_echo_EchoClientActivity.h"

#include "SocketUtils.h"

//服务端：启动监听
//流程:socket()->listen()->accept()->recv()->send()_close()
void JNICALL Java_com_echo_EchoServerActivity_nativeStartTcpServer(
		JNIEnv *env, jobject obj, jint port) {
	int serverSocket = NewTcpSocket(env, obj);

	if (NULL == env->ExceptionOccurred()) {
		//绑定
		BindSocketToPort(env, obj, serverSocket, (unsigned short) port);
		if (NULL != env->ExceptionOccurred()) {
			goto exit;
		}

		//如果端口是0，打印出当前随机分配的端口
		if (0 == port) {
			GetSocketPort(env, obj, serverSocket);
			if (NULL != env->ExceptionOccurred()) {
				goto exit;
			}
		}

		//监听 链接4
		ListenOnSocket(env, obj, serverSocket, 4);
		if (NULL != env->ExceptionOccurred()) {
			goto exit;
		}

		//
		int clientSocket = AcceptOnSocket(env, obj, serverSocket);
		if (NULL != env->ExceptionOccurred()) {
			goto exit;
		}

		char buffer[MAX_BUFFER_SIZE];
		ssize_t recvSize;
		ssize_t sentSize;

		while (1) {
			//接收
			recvSize = ReceiveFromSocket(env, obj, clientSocket, buffer,
			MAX_BUFFER_SIZE);

			if ((0 == recvSize) || (NULL != env->ExceptionOccurred())) {
				break;
			}

			//发送
			sentSize = SendToSocket(env, obj, clientSocket, buffer,
					(size_t) recvSize);
			if ((0 == sentSize) || (NULL != env->ExceptionOccurred())) {
				break;
			}
		}

		//close the client socket
		close(clientSocket);

	}

	exit: if (serverSocket > 0) {
		close(serverSocket);
	}
}

//客户端：连接
void JNICALL Java_com_echo_EchoClientActivity_nativeStartTcpClient(
		JNIEnv *env, jobject obj, jstring ip, jint port, jstring message) {

	int clientSocket = NewTcpSocket(env, obj);
	if (NULL == env->ExceptionOccurred()) {
		const char* ipAddress = env->GetStringUTFChars(ip, NULL);

		if (NULL == ipAddress) {
			goto exit;
		}
		ConnectToAddress(env, obj, clientSocket, ipAddress,
				(unsigned short) port);
		//释放ip
		env->ReleaseStringUTFChars(ip, ipAddress);

		//connect exception check
		if (NULL != env->ExceptionOccurred()) {
			goto exit;
		}

		const char* messageText = env->GetStringUTFChars(message, NULL);
		if (NULL == messageText) {
			goto exit;
		}

		//这里的size不用release??
		jsize messageSize = env->GetStringUTFLength(message);
		SendToSocket(env, obj, clientSocket, messageText, messageSize);

		//
		env->ReleaseStringUTFChars(message, messageText);

		if (NULL != env->ExceptionOccurred()) {
			goto exit;
		}

		char buffer[MAX_BUFFER_SIZE];

		ReceiveFromSocket(env, obj, clientSocket, buffer, MAX_BUFFER_SIZE);
	}

	exit: if (clientSocket > -1) {
		close(clientSocket);
	}
}

//启动udp服务端
void JNICALL Java_com_echo_EchoServerActivity_nativeStartUdpServer(
		JNIEnv *, jobject, jint) {

}