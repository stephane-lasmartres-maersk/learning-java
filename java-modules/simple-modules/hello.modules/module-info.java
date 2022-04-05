module hello.modules {
    exports com.maersk.modules.hello;
    provides com.maersk.modules.hello.HelloInterface with com.maersk.modules.hello.HelloModules;    
}
