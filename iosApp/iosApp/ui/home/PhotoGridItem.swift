//
//  MovieGridItem.swift
//  iosApp
//
//  Created by Mohd Isa Andi Matalatta on 20/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PhotoGridItem: View {
    let photo: Photo
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8){
            ZStack{
                
                AsyncImage(url: URL(string: photo.urlM)){image in
                    image.resizable()
                }placeholder: {
                    Color.gray
                }
                
            }
            .frame(maxWidth: .infinity, idealHeight: .infinity)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            
            Text(photo.title)
                .font(.title3)
                .fontWeight(.bold)
                .lineLimit(1)
            
            
            
        }
        .frame(maxWidth: .infinity, maxHeight: 260)
    }
}

struct PhotoGridItem_Previews: PreviewProvider {
    static var previews: some View {
        PhotoGridItem(photo: samplePhoto)
    }
}
