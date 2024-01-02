import PyPDF2
import fitz  # PyMuPDF
import json

def extract_text_with_pypdf2(pdf_path):
    with open(pdf_path, 'rb') as file:
        pdf_reader = PyPDF2.PdfReader(file)
        text = ''
        for page_num in range(len(pdf_reader.pages)):
            page = pdf_reader.pages[page_num]
            text += page.extract_text()
    return text

def extract_text_with_pymupdf(pdf_path):
    text = ''
    pdf_document = fitz.open(pdf_path)
    for page_num in range(pdf_document.page_count):
        page = pdf_document.load_page(page_num)
        text += page.get_text()
    return text

def structure_data(text):
    # Replace this with your logic to extract entities like description, quantity, amount, etc.
    # For simplicity, let's assume a basic structure here.
    statements = text.split('-')
    # with open('output_text.txt','a') as f:

    data = []
    for line in statements:
        items = line.split('\n')
        if len(items) > 3 :
            # print(items)
            entry = {
                        "item":items,
                        "description": items[0].strip(),
                        "quantity": items[1].strip(),
                        "amount": items[2].strip()
            }
            data.append(entry)
    return data

def save_to_json(data, json_path):
    with open(json_path, 'w') as json_file:
        json.dump(data, json_file, indent=2)

def main(pdf_path, json_path):
    # Choose either PyPDF2 or PyMuPDF (fitz) based on your preference or requirements
    text = extract_text_with_pypdf2(pdf_path)
    # text = extract_text_with_pymupdf(pdf_path)
   
    
    structured_data = structure_data(text)
    save_to_json(structured_data, json_path)

if __name__ == "__main__":
    pdf_file_path = 'sample.pdf'
    json_output_path = 'output.json'
    main(pdf_file_path, json_output_path)
