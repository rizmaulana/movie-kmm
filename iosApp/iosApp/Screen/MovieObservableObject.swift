//
//  MovieObservableObject.swift
//  iosApp
//
//  Created by Rizki Maulana on 23/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Combine
import shared
public class MovieObservableObject : ObservableObject {
    
    var viewModel : MovieViewModel
    
    /**
     *
     * state flow acts as a state for swift ui here
     *
     */
    @Published private(set) var movieAsync: AsyncResult
    
    @Published private(set) var selectedMovieState: Movie
    
    /**
     **
     *fusing the .asObserveable extension funstion we get the wrapped viewmodel and the stateflow
     */
    init(wrapped: MovieViewModel) {
        viewModel = wrapped
        
        movieAsync = wrapped.movies.value as! AsyncResult
        selectedMovieState = wrapped.selectedMovie.value as! Movie
        
        (wrapped.movies.asPublisher() as AnyPublisher<AsyncResult, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$movieAsync)
        
        (wrapped.selectedMovie.asPublisher() as AnyPublisher<Movie, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$selectedMovieState)
    }
    
}





public extension MovieViewModel {
    
    func asObserveableObject() -> MovieObservableObject{
        return MovieObservableObject(wrapped: self)
    }
    
}
