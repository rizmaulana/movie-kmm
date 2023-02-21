
import SwiftUI
import shared

struct MovieView : View {

 @ObservedObject var state = ViewModelsProvider().getMovieViewModel().asObserveableObject()


}