; March 25, 2023 | 12:17 
; creating the bootloader
org 0x7C00
bits 16

%define ENDL 0x0D, 0x0A

start:
    jmp main

; this only prints something on the creen
; parameters:
;       - ds:si points to string

puts:
    ; save registers we will modify
    push si
    push ax
    push bx

.loop:
    lodsb
    or al, al                           ; loads next character in al
    jz .done                           ; verify if next character is null?

    mov ah, 0x0E                        ; calls bios interrupt
    mov bh, 0                           ; set page number to 0
    int 0x10

    jmp .loop


.done:
    pop bx 
    pop ax 
    pop si
    ret 


main:
    ; setup data segments
    mov ax, 0                           ; can't set ds/es directly
    mov ds, ax 
    mov es, ax

    ; setup stack
    mov ss, ax 
    mov sp, 0x7C00      ; stack grows downward from where we are loaded
                        ; in memory

    ; prints message
    mov si, msg_hello
    call puts
    hlt 


.halt:
    jmp .halt


msg_hello : db "Hello there, Ralph Maron Eda is here!", ENDL, 0

times 510-($-$$) db 0
dw 0AA55h

