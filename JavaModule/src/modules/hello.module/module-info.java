
module hello.module {
	exports pack.code;

	provides pack.code.MyInterface with pack.code.Myclass;
}