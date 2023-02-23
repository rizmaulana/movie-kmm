import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        ModuleComponentKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
