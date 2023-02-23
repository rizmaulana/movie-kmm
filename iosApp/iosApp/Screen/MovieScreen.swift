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

struct MovieScreen: View {
    @ObservedObject var viewModel = ViewModelProvider().getMovieViewModel().asObserveableObject()
    
    var body: some View {
        VStack(alignment: .leading){
            HStack {
                Spacer()
            }
            switch viewModel.movieAsync {
            case is AsyncResultLoading:
                loadingView()
            case is AsyncResultSuccess<List<Movie>>:
                contentView()
            case is AsyncResultError:
                errorView()
            default:
                Spacer()
                
            }
            Spacer()
        }
        .background(Color.black.ignoresSafeArea())
        .navigationBarHidden(true)
        
        
    }
    
    @ViewBuilder func contentView() -> some View {
        VStack(alignment: .leading){
            Text("Now Playing")
                .foregroundColor(Color.white)
                .fontWeight(Font.Weight.bold)
                .padding(.horizontal)
            Text("Watch your favorites movie of the year")
                .foregroundColor(Color.gray)
                .padding(.horizontal)
            Spacer()
                .frame(height: 8)
            moviePosterView()
            Spacer()
                .frame(height: 8)
            Text("Select movie poster to see detail")
                .foregroundColor(Color.white)
                .fontWeight(Font.Weight.bold)
                .padding(.horizontal)
            movieDetailView()
        }
    }
    
    @ViewBuilder func loadingView() -> some View {
        Spacer()
        VStack(alignment: .center){
            HStack{
                Spacer()
            }
            Text("Loading ...")
                .foregroundColor(Color.white)
        }
        
    }
    
    @ViewBuilder func errorView() -> some View {
        Spacer()
        VStack(alignment: .center){
            HStack{
                Spacer()
            }
            Text(":(")
                .foregroundColor(Color.white)
            Text("Something wrong")
                .foregroundColor(Color.white)
        }
        
    }
    
    
    @ViewBuilder func moviePosterView() -> some View {
        
        ScrollView(.horizontal, showsIndicators: false){
            LazyHStack{
                VStack(alignment: .leading){
                    
                }
            }
        }
    }
    
    
    @ViewBuilder func movieDetailView() -> some View {
        
    }
    
}




struct MovieScreen_Previews: PreviewProvider {
    static var previews: some View {
        MovieScreen()
    }
}
