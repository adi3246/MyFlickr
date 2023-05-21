//
//  DetailScreen.swift
//  iosApp
//
//  Created by Mohd Isa Andi Matalatta on 21/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailScreen: View {
    let photo: Photo
    @State private var inputImage: UIImage?
    @State private var showingAlert = false
    
    var body: some View {
    
        ScrollView{
            
            VStack{
                
                ZStack{
                    AsyncImage(url: URL(string: photo.urlM)){image in
                        image.resizable().scaledToFit()
                    }placeholder: {
                        ProgressView()
                    }
                }
                .frame(maxWidth: .infinity, minHeight: 300, maxHeight: 300)
                
                VStack(alignment:.leading, spacing: 12){
                    Text(photo.title)
                        .font(.title)
                        .fontWeight(.bold)
                        .fixedSize(horizontal: false, vertical: true)
                }
                .padding(20)
                .background(.black)
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
        }
        .toolbar {
            Button {
                getData(from: URL(string: photo.urlM)!) { data, response, error in
                    guard let data = data, error == nil else { return }
                    print("Download Finished")
                    // always update the UI from the main thread
                        DispatchQueue.main.async() {
                            let uiImage = UIImage(data: data)
                            let imageSaver = ImageSaver()
                            imageSaver.writeToPhotoAlbum(image: uiImage!)
                            showingAlert = true
                        }
                }
            } label: {
                Label("Download", systemImage: "square.and.arrow.down")
            } .alert("Photo saved to gallery", isPresented: $showingAlert) {
                Button("OK", role: .cancel) { }
            }
        }
    }
    
    func getData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
    }
}

struct DetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        DetailScreen(photo: samplePhoto)
    }
}

