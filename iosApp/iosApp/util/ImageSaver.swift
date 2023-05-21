//
//  ImageSaver.swift
//  iosApp
//
//  Created by Mohd Isa Andi Matalatta on 21/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit

class ImageSaver: NSObject {
    func writeToPhotoAlbum(image: UIImage) {
        UIImageWriteToSavedPhotosAlbum(image, self, #selector(saveCompleted), nil)
    }

    @objc func saveCompleted(_ image: UIImage, didFinishSavingWithError error: Error?, contextInfo: UnsafeRawPointer) {
        print("Save finished!")
    }
}
