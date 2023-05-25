//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Mohd Isa Andi Matalatta on 20/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeScreen {
    @MainActor class HomeViewModel: ObservableObject {
        
        private let getPhotosUseCase = GetPhotosUseCase.init()
        
        @Published private(set) var photos:[Photo] = []
        @Published private(set) var isLoading: Bool = false
        @Published private(set) var noResult: Bool = false
        
        private var currentPage = 1
        private var isRefresh = false
        private(set) var loadFinished: Bool = false
        
        func loadPhotos(name: String) async {
            let finalName = (name.isEmpty) ? "Electrolux" : name
            
            if currentPage <= 3 {
                if isLoading {
                    return
                }
                isLoading = true
                do {
                    
                    let photos = try await getPhotosUseCase.invoke(page: Int32(currentPage), name: finalName)
                    isLoading = false
                    loadFinished = photos.isEmpty
                    
                    currentPage += 1
                    
                    if isRefresh {
                        self.photos = photos
                        isRefresh = false
                    } else {
                        self.photos = self.photos + photos
                    }
                    
                    noResult = self.photos.isEmpty
                } catch {
                    isLoading = false
                    loadFinished = true
                    noResult = false
                    
                    print(error.localizedDescription)
                 }
            } else {
                isLoading = false
                loadFinished = true
                noResult = false
            }
        }
        
        func resetPageCount() {
            self.currentPage = 1
        }
        
        func setIsRefresh() {
            resetPageCount()
            self.isRefresh = true
        }
    }
}
