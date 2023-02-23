//
//  MovieScreen.swift
//  iosApp
//
//  Created by Rizki Maulana on 21/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Foundation
import SDWebImageSwiftUI

struct MovieScreen: View {
    @ObservedObject var state = ViewModelProvider().getMovieViewModel().asObserveableObject()
    
    var body: some View {
        VStack(spacing: 0){
            ScrollView(showsIndicators: false) {
                VStack {
                    switch state.movieAsync {
                    case is AsyncResultSuccess:
                        contentView(result: state.movieAsync as! AsyncResultSuccess)
                    case is AsyncResultError:
                        errorView()
                    default:
                        loadingView()
                    }
                }
            }
            
        }
        .background(Color.black.ignoresSafeArea())
        .navigationBarHidden(true)
        
        
    }
    
    @ViewBuilder func contentView(result : AsyncResultSuccess) -> some View {
        VStack(alignment: .leading){
            Text("Now Playing")
                .foregroundColor(Color.white)
                .fontWeight(Font.Weight.bold)
                .padding(.horizontal)
            Text("Watch your favorites movie of the year")
                .foregroundColor(Color.gray)
                .padding(.horizontal)
            moviePosterView(movies: result.data)
            Spacer().frame(height: 32)
            Text("Select movie poster to see detail")
                .foregroundColor(Color.white)
                .fontWeight(Font.Weight.bold)
                .padding(.horizontal)
            Spacer().frame(height: 24)
            movieDetailView(movie: state.selectedMovieState)
            
        }
    }
    
    @ViewBuilder func loadingView() -> some View {
        VStack(alignment: .center){
            HStack{
                Spacer()
            }
            Text("Loading ...")
                .foregroundColor(Color.white)
        }.background(Color.black)
        
    }
    
    @ViewBuilder func errorView() -> some View {
        Spacer()
        VStack(alignment: .center){
            HStack{
                Spacer()
            }
            Text(":(")
                .foregroundColor(Color.white)
                .font(.system(size: 36))
            Spacer().frame(height: 16)
            Text("Something wrong")
                .foregroundColor(Color.white)
            Spacer().frame(height: 8)
            Button("Try again"){
                state.viewModel.loadMovies()
            }
        }.background(Color.black)
        
    }
    
    
    @ViewBuilder func moviePosterView(movies: [Movie]) -> some View {
        ScrollView(.horizontal, showsIndicators: false){
            LazyHStack(alignment: VerticalAlignment.top){
                ForEach(movies.indices){ index in
                    let movieItem = movies[index]
                    VStack(alignment: .leading){
                        WebImage(url: URL(string: movieItem.posterUrl))
                            .resizable()
                            .frame(width: 130, height: 200)
                            .cornerRadius(14)
                        Spacer().frame(height: 8)
                        Text(movieItem.title).foregroundColor(Color.white)
                            .font(.system(size: 14))
                            .fontWeight(Font.Weight.bold)
                    }.frame(width: 135)
                        .onTapGesture {
                            state.viewModel.selectMovie(movie: movieItem)
                        }
                }
            }
            
        }
    }
    
    
    @ViewBuilder func movieDetailView(movie: Movie) -> some View {
        if movie.id == 0 {
            Spacer()
        }else{
            VStack(alignment: .leading){
                ZStack(alignment: .leading){
                    VStack(alignment: .leading){
                        WebImage(url: URL(string: movie.bannerUrl))
                            .resizable()
                            .frame(height: 160)
                        Text(movie.title).foregroundColor(Color.white)
                            .font(.system(size: 14))
                            .fontWeight(Font.Weight.bold)
                            .padding(EdgeInsets(top: 8, leading: 135, bottom: 0, trailing: 16))
                        Text(movie.releaseDateFormatted()).foregroundColor(Color.white)
                            .font(.system(size: 14))
                            .padding(EdgeInsets(top:0, leading: 135, bottom: 0, trailing: 16))
                    }
                  
                    WebImage(url: URL(string: movie.posterUrl))
                        .resizable()
                        .frame(width: 100, height: 140)
                        .cornerRadius(14)
                        .padding(EdgeInsets(top: 60, leading: 16, bottom: 0, trailing: 0))
                }
              
              
                
            }
            Text(movie.description_).foregroundColor(Color.white)
                .font(.system(size: 14))
                .padding(EdgeInsets(top:16, leading: 16, bottom: 0, trailing: 16))
        }
        
        
    }
    
}


public extension Movie {
    func releaseDateFormatted() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let dateFormatterResult = DateFormatter()
        dateFormatterResult.dateFormat = "dd MMMM yyyy"
        let date : Date? = dateFormatter.date(from: self.releaseDate)
        let releaseDateReadable = dateFormatterResult.string(from: date!)
        return "Release on \(releaseDateReadable)"
    }
}



struct MovieScreen_Previews: PreviewProvider {
    static var previews: some View {
        MovieScreen()
    }
}
