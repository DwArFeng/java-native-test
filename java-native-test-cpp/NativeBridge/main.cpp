/**********************************
2017-9-5 21:02:51
������Ҫ��java���õķ������÷�����java�ӿ��ڲ���������һ��
Ԥ�������Ŀ���Ǳ�¶�������ⲿ���á�
************************/
#ifdef LIBAPI 
#else 
#define  LIBAPI  extern "C" __declspec(dllimport)     
#endif 

LIBAPI int showInteger();
LIBAPI char* describe();
LIBAPI char* helloWorldRepeat(char*);

using namespace NativeTest;
using namespace System;

char* describe(){
	NativeTest::Repeater^ r = gcnew NativeTest::Repeater();
	return (char*)(void*)System::Runtime::InteropServices::Marshal::StringToHGlobalAnsi(r->describe()); 
}

int showInteger(){
	return 11;
}

char* helloWorldRepeat(char* ch){
	System::String^ str = gcnew String(ch);
	NativeTest::Repeater^ r = gcnew NativeTest::Repeater();
	return (char*)(void*)System::Runtime::InteropServices::Marshal::StringToHGlobalAnsi(r->helloWorldRepeat(str)); 
}
