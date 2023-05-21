//
//  HomeScreen.swift
//  iosApp
//
//  Created by Mohd Isa Andi Matalatta on 20/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreen: View {
    @StateObject var viewModel = HomeViewModel()
    @State private var searchTerm = ""
    
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)
    
    var body: some View {
        NavigationStack{
            
            ScrollView{
                LazyVGrid(columns: gridColumns, spacing: 16){
                    
                    ForEach(viewModel.photos, id: \.id){photo in
                        
                        NavigationLink(value: photo){
                            PhotoGridItem(photo: photo)
                                .task {
                                    if photo == viewModel.photos.last && !viewModel.isLoading && !viewModel.loadFinished {
                                        await viewModel.loadPhotos(name: searchTerm)
                                    }
                                }
                        }
                        .buttonStyle(PlainButtonStyle())
                    }
                    
                    if viewModel.isLoading {
                        Section(footer: ProgressView()){}
                    }
                    
                }
                .padding(.horizontal, 12)
                .navigationDestination(for: Photo.self){photo in
                    DetailScreen(photo: photo)
                }
                
            }
            .refreshable {
                Task {
                    searchTerm = "Electrolux"
                    viewModel.setIsRefresh()
                    await viewModel.loadPhotos(name: searchTerm)
                }
            }
            .navigationTitle("My Flickr")
            
        }
        .task {
            await viewModel.loadPhotos(name: searchTerm)
        }
        .searchable(text: $searchTerm)
        .onSubmit(of: .search) {
            Task {
                viewModel.setIsRefresh()
                if !searchTerm.isEmpty && searchTerm.count > 3 {
                    await viewModel.loadPhotos(name: searchTerm)
                }
            }
        }
    }

}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
